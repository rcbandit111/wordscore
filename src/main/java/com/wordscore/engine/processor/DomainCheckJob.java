package com.wordscore.engine.processor;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DomainCheckJob extends ServiceFactory implements Job {

    // http://www.nirsoft.net/whois_servers_list.html

    private final static String WHO ="cnn.com";
    private final static String WHOIS_HOST = "whois.verisign-grs.com";
    private final static int WHOIS_PORT = 43;

    public DomainCheckJob() {
    }

    @Override
    public void execute(JobExecutionContext context) {

        int data;
        Socket socket = null;

        String query = WHO + "\r\n";
        byte buf[] = query.getBytes();

        try {
            socket = new Socket(WHOIS_HOST, WHOIS_PORT);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            out.write(buf);
            out.flush();
            while ((data = in.read()) != -1) {
                System.out.print((char) data);
            }
            System.out.print("\nDone\n");
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        } finally {
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException ex) {
                }
            }
        }
    }
}
