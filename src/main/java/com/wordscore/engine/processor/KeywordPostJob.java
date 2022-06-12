package com.wordscore.engine.processor;

import com.wordscore.engine.database.entity.ProcessedWords;
import com.wordscore.engine.database.service.ProcessedWordsService;
import com.wordscore.engine.service.generator.WordsGenerator;
import okhttp3.*;
import okio.Buffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeywordPostJob extends ServiceFactory implements Job {

    public KeywordPostJob() {
    }

    @Override
    public void execute(JobExecutionContext context) {

        try {
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(
                        "C:\\in_progress\\test.txt"));
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);

//                    Thread.sleep(20);
                    Optional<ProcessedWords> isFound = processedWordsService.findByKeyword(line);

                    if(!isFound.isPresent()){
                        ProcessedWords obj = ProcessedWords.builder()
                                .keyword(line)
                                .createdAt(LocalDateTime.now())
                                .build();
                        processedWordsService.save(obj);
                    }

                    // read next line
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }





//        String word = wordsGenerator.generateRandomKeyword();
//
//        System.out.println(word);
//
////        Optional<ProcessedWords> isFound = processedWordsService.findByKeyword(word);
////
////        if(!isFound.isPresent()){
//            ProcessedWords obj = ProcessedWords.builder()
//                    .keyword(word)
//                    .createdAt(LocalDateTime.now())
//                    .build();
//            processedWordsService.save(obj);
////        }



//        System.out.println("Random word " + wordsGenerator.generateRandomString(5, 10));
//
//        OkHttpClient httpClient = new OkHttpClient();
//
//        JSONArray arrayObj = new JSONArray();
//        JSONObject jsonObject = new JSONObject();
//        JSONArray array = new JSONArray();
//        try {
//            jsonObject.put("location_code", "2840");
//
//            array.putAll(List.of("phone"));
//            jsonObject.put("keywords", array);
//            arrayObj.put(jsonObject);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        String json = arrayObj.toString();
//
//        RequestBody payload = RequestBody.create(
//                MediaType.parse("application/json"), json);
//
//        Request request = new Request.Builder()
//                .url("https://api.dataforseo.com/v3/keywords_data/google_ads/keywords_for_keywords/live")
//                .addHeader("Authorization", Credentials.basic("peter.penzov@gmail.com", "0aac4d174e63d300"))
//                .post(payload)
//                .build();
//
//        try {
//            getBodyAsString(request);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        try (Response response = httpClient.newCall(request).execute()) {
//
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            // Get response headers
//            Headers responseHeaders = response.headers();
//            for (int i = 0; i < responseHeaders.size(); i++) {
//                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//            }
//
//            // Get response body
//            System.out.println(response.body().string());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void getBodyAsString(Request request) throws IOException {
        try(var buffer = new Buffer()) {
            request.body().writeTo(buffer);
            System.out.println(buffer.readUtf8());
        }
    }

    public void removeLineFromFile(String lineToRemove, File f) throws FileNotFoundException, IOException{
        //Reading File Content and storing it to a StringBuilder variable ( skips lineToRemove)
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(f)) {
            String currentLine;
            while(sc.hasNext()){
                currentLine = sc.nextLine();
                if(currentLine.equals(lineToRemove)){
                    continue; //skips lineToRemove
                }
                sb.append(currentLine).append("\n");
            }
        }
        //Delete File Content
        PrintWriter pw = new PrintWriter(f);
        pw.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
        writer.append(sb.toString());
        writer.close();
    }


}
