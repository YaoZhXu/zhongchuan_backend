package com.shu.backend.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shu.backend.dto.ChatDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

@Component
public class ChatFacade {

    @Resource
    private ObjectMapper objectMapper;

    public String chat(ChatDTO messages) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, objectMapper.writeValueAsString(messages));
        Request request = new Request.Builder()
                .url("http://47.102.104.144:8081/chat")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        StringBuilder sb = new StringBuilder();

        try (Response response = client.newCall(request).execute();
             Reader charStream = response.body().charStream();
             BufferedReader reader = new BufferedReader(charStream)) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line.replaceAll("data: ", ""));
            }
        }
        return sb.toString();
    }
}
