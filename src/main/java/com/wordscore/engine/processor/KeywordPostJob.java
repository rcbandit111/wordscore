package com.wordscore.engine.processor;

import com.opencsv.CSVWriter;
import com.wordscore.engine.database.entity.ProcessedWords;
import com.wordscore.engine.rest.dto.UpdateKeywordRequestDTO;
import okhttp3.Request;
import okio.Buffer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class KeywordPostJob extends ServiceFactory implements Job {

    // SELECT pg_size_pretty(pg_database_size('wordscore_engine'));

    public KeywordPostJob() {
    }


    @Override
    public void execute(JobExecutionContext context) {

//        try {
//            FileInputStream fstream = new FileInputStream("C:\\in_progress\\test.txt");
//            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
//
//            String strLine;
//
//            while ((strLine = br.readLine()) != null) {
//
//                String[] result = strLine.split("\t");
//                String[] newArr = Arrays.copyOf(result, result.length - 16);
//
//                String keyword = newArr[0];
//                String score = newArr[1];
//
//                Optional<ProcessedWords> isFound = processedWordsService.findByKeyword(keyword);
//
//                if(isFound.isPresent())
//                {
//                    if(score.equals("NA")){
//                        score = "0";
//                    }
//
//                    UpdateKeywordRequestDTO obj = UpdateKeywordRequestDTO.builder().keyword(keyword).seoScoreUs(BigDecimal.valueOf(Long.parseLong(score))).build();
//                    processedWordsService.updateBySeoScoreUs(obj);
//                }
//                else
//                {
//                    if(score.contains("NA")){
//                        continue;
//                    }
//                    ProcessedWords obj = ProcessedWords.builder()
//                            .keyword(keyword)
//                            .seoScoreUs(BigDecimal.valueOf(Long.parseLong(score)))
//                            .isComDomainAvailable(false)
//                            .createdAt(LocalDateTime.now())
//                            .build();
//                    processedWordsService.save(obj);
//                }
//
//                System.out.println("keyword insert " + keyword);
//
//
//
////                System.out.println(Arrays.toString(newArr));
//            }
//
//            System.out.println("\n\n\n\nCOMPLETED\n\n\n\n");
//
//            fstream.close();
//        } catch (Exception e){
//            e.printStackTrace();
//        }




//        try {
//            BufferedReader reader;
//            try {
//                reader = new BufferedReader(new FileReader(
//                        "C:\\in_progress\\job.csv"));
//                String line = reader.readLine();
//                while (line != null) {
//                    System.out.println(line);
//
////                    Thread.sleep(20);
//                    Optional<ProcessedWords> isFound = processedWordsService.findByKeyword(line);
//
//                    if(!isFound.isPresent()){
//                        ProcessedWords obj = ProcessedWords.builder()
//                                .keyword(line)
//                                .createdAt(LocalDateTime.now())
//                                .build();
//                        processedWordsService.save(obj);
//                    }
//
//                    // read next line
//                    line = reader.readLine();
//                }
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }



//        try {
//            String fileName = "C:\\in_progress\\job.csv";
//
//            List<CsvLine> beans = new CsvToBeanBuilder(new FileReader(fileName))
//                    .withType(CsvLine.class)
//                    .withSkipLines(1)
//                    .build()
//                    .parse();
//
//            for(CsvLine item: beans){
//
//                Optional<ProcessedWords> isFound = processedWordsService.findByKeyword(item.getKeyword());
//
//                if(isFound.isPresent())
//                {
//                    UpdateKeywordRequestDTO obj = UpdateKeywordRequestDTO.builder().keyword(item.getKeyword()).seoScoreUs(item.getCpc()).build();
//                    processedWordsService.updateBySeoScoreUs(obj);
//                }
//                else
//                {
//                    ProcessedWords obj = ProcessedWords.builder()
//                            .keyword(item.getKeyword())
//                            .seoScoreUs(item.getCpc())
//                            .createdAt(LocalDateTime.now())
//                            .build();
//                    processedWordsService.save(obj);
//                }
//
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }




//        try {
//            BufferedReader reader;
//            try {
//                reader = new BufferedReader(new FileReader(
//                        "C:\\in_progress\\test.txt"));
//                String line = reader.readLine();
//                while (line != null) {
//                    System.out.println(line);
//
////                    Thread.sleep(20);
//                    Optional<ProcessedWords> isFound = processedWordsService.findByKeyword(line);
//
//                    if(!isFound.isPresent()){
//                        ProcessedWords obj = ProcessedWords.builder()
//                                .keyword(line)
//                                .createdAt(LocalDateTime.now())
//                                .build();
//                        processedWordsService.save(obj);
//                    }
//
//                    // read next line
//                    line = reader.readLine();
//                }
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }





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

    public void print(CsvLine obj)
    {
//        System.out.println("Keyword: " + obj.getKeyword());
//        System.out.println("cpc: " + obj.getCpc());
    }


}
