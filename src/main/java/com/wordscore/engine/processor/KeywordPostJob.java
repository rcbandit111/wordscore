package com.wordscore.engine.processor;

import okhttp3.*;
import okio.Buffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.IOException;
import java.util.List;

public class KeywordPostJob extends ServiceFactory implements Job {

    public KeywordPostJob() {
    }

    @Override
    public void execute(JobExecutionContext context) {
        System.out.println(wordsGenerator.generateRandomKeyword());

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

}
