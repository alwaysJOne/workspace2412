package com.kh.springai.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/ai")
@RestController
public class AiController {

    @Autowired
    private ChatModel chatModel;

    @GetMapping("/test")
    public Map<String, Object> testChatModel() {

        System.out.println("chatModel is " + chatModel.toString());

        //ChatClient를 생성
        ChatClient chatClient = ChatClient.builder(chatModel).build();

        String aiResponse = chatClient.prompt("공부관련 명언을 세 개 알려줘.").call().content();

        Map<String, Object> map = new HashMap<>();
        map.put("aiResponse", aiResponse);
        map.put("status", "success");

        return map;
    }

    //DALL-E 3 기반 AI 이미지 생성
    @PostMapping("/generate-image")
    public Map<String, Object> generateImage(@RequestBody Map<String, String> request) {
        String prompt = request.get("prompt");

        if(prompt == null || prompt.trim().isEmpty()) {

        }
    }
}
