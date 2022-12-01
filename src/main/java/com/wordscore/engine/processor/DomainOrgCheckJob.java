package com.wordscore.engine.processor;

import com.wordscore.engine.database.entity.ProcessedWords;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DomainOrgCheckJob extends ServiceFactory implements Job {

    // http://www.nirsoft.net/whois_servers_list.html

    //    private final static String WHO ="cnn.org";
    private final static String WHOIS_HOST = "whois.pir.org";
    private final static int WHOIS_PORT = 43;

    public DomainOrgCheckJob() {
    }

    @Override
    public void execute(JobExecutionContext context) {

        Optional<ProcessedWords> isFound = processedWordsService.findRandomKeyword();
        if(isFound.isPresent()){
            String payload = isFound.get().getKeyword() + ".com";

            System.out.println("Checking keyword: " + isFound.get().getKeyword());
            long id = isFound.get().getId();
            String domain = payload.replaceAll("\\s+", "");
            System.out.println("Checking org domain: " + domain);

            try (Socket socket = new Socket(WHOIS_HOST, WHOIS_PORT)) {
                OutputStream out = socket.getOutputStream();
                out.write((domain + "\r\n").getBytes());

                try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String line;
                    while ((line = input.readLine()) != null) {
                        if (line.contains("Registry Expiry Date")) {
                            line = line.substring(line.indexOf(':') + 1).trim();
                            System.out.println("---> " + line);
                            break; // don't need to read any more input
                        }
                    }

                    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
                    if(line != null){
                        final LocalDateTime dt = LocalDateTime.parse(line, formatter);
                        System.out.println("---> " + dt);
                        System.out.println("---> Not available " + domain + " " + dt);

                        // Update keywords into database - "false" for not available - domain is registered
                        processedWordsService.updateOrgDomainById(id, false);
                    } else {
                        System.out.println("---> Available " + domain + " id " + id);

                        // Update keywords into database - "true" for available - domain is available for registration
                        processedWordsService.updateOrgDomainById(id, true);
                    }
                }

                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
