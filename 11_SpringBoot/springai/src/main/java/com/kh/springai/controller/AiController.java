package com.kh.springai.controller;

import com.kh.springai.dto.SqlResponse;
import com.kh.springai.entity.Movie;
import com.kh.springai.service.DateTimeTools;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/ai")
@RestController
public class AiController {

    @Autowired
    private ChatModel chatModel;

    @Autowired
    private ImageModel imageModel;

    @Autowired
    private DateTimeTools dateTimeTools;

    @Value("classpath:/schema.sql")
    private Resource schema;

    @Value("classpath:/sql-prompt-template.st")
    private Resource sqlPromptTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        Map<String, Object> response = new HashMap<>();

        try {
            String prompt = request.get("prompt");

            if(prompt == null || prompt.trim().isEmpty()) {
                response.put("status", "error");
                response.put("error", "이미지 설명을 입력해주세요.");
                return response;
            }

            ImageOptions options = ImageOptionsBuilder.builder()
                    .model("dall-e-3")
                    .width(1024)
                    .height(1024)
                    .build();

            //이미지생성
            ImagePrompt imagePrompt = new ImagePrompt(prompt, options);
            ImageResponse imageResponse = imageModel.call(imagePrompt);
            String imageUrl = imageResponse.getResult().getOutput().getUrl();

            response.put("status", "success");
            response.put("imageUrl", imageUrl);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("error", e.getMessage());
        }

        return response;
    }

    /*
    PromptTemplate이란?
    - LLM에게 전달할 프롬프트를 템플릿화해서 재사용 가능하게 만드는 기술
    - {변수명} 향테로 플레이스홀더를 사용하여 동적으로 내용을 바꿀 수 있다.
    ex) 안녕하세요! 제 이름은 {name}입니다. -> name파라미터에 따라서 다양한 인사말을 생성
     */
    @PostMapping("/prompt-template")
    public Map<String, Object> promptTemplate(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();

        try {
            String name = request.get("name");

            if(name == null || name.trim().isEmpty()) {
                response.put("status", "error");
                response.put("error", "이름을 입력해주세요.");
                return response;
            }

            PromptTemplate promptTemplate = new PromptTemplate("안녕하세요 제 이름은 {name}입니다. 오늘 날씨에 대해서 알려주세요.");
            Prompt prompt = promptTemplate.create(Map.of("name", name));

            ChatClient chatClient = ChatClient.builder(chatModel).build();
            String result = chatClient.prompt(prompt)
                    .call()
                    .content();


            response.put("status", "success");
            response.put("result", result);
            response.put("type", "prompt-template");
        }
        catch (Exception e) {
            response.put("status", "error");
            response.put("error", e.getMessage());
        }

        return response;
    }

    //영화 리스트 생성
    @PostMapping("/chat-movie")
    public Map<String, Object> chatMovie(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();

        try {
            String directorName = request.get("directorName");
            if(directorName == null || directorName.trim().isEmpty()) {
                response.put("status", "error");
                response.put("error", "감독 이름을 입력해주세요.");
                return response;
            }

            String template = """
                    Generate a list of movies directed by {directorName}. If the director is unknown, return null.
                    Each movie object must contain the following
                    - “title” (string): The title of the video.
                    - "year" (string): release year.
                    
                    한국 영화는 한글로 표기해줘.
                    Format: {format} only.
                    """;

            //new ParameterizedTypeReference<List<Movie>>() {} -> 제네릭 타입 정보를 유지한채로 json응답을 List<Movie>형태로 파싱해줘.

            ChatClient chatClient = ChatClient.builder(chatModel).build();
            List<Movie> movieList = chatClient.prompt()
                    .user(userSpec -> userSpec.text(template)
                            .param("directorName", directorName)
                            .param("format","json"))
                    .call()
                    .entity(new ParameterizedTypeReference<List<Movie>>() {});

            response.put("status", "success");
            response.put("result", movieList);
            response.put("type", "movie-list");
            response.put("directorName", directorName);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("error", e.getMessage());
        }
        return response;
    }

    /*
    function-calling이란
    - LLM이 직접 외부함수를 호출하여 실시간 정보를 가져오는 방식
    - LLM이 사용자 질문을 분석해서 필요한 정보를 자동으로 선택하여 호출할 수 있음.

    동작방식
    1. ChatClient .tools() 사용할 수 있는 도구를 등록
    2. 사용자 질문을 전달
    3. 질문분석 후 필요한 도구를 판단해서 사용하여 정보 획득
     */
    @PostMapping("/function-calling-ko")
    public Map<String, Object> functionCallingKo(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();

        try {
            String question = request.get("question");
            if(question == null || question.trim().isEmpty()) {
                response.put("status", "error");
                response.put("error", "질문을 입력해주세요.");
                return response;
            }

            ChatClient chatClient = ChatClient.builder(chatModel).build();
            String result = chatClient.prompt(question)
                    .tools(dateTimeTools)
                    .call()
                    .content();

            response.put("status", "success");
            response.put("result", result);
            response.put("type", "function-calling-ko");
        }catch (Exception e) {
            response.put("status", "error");
            response.put("error", e.getMessage());
        }

        return response;
    }

    //자연어로 요청한부분을 llm을 통해 sql을 구성해서 추출하기
    @GetMapping("/sql")
    public SqlResponse sqlQuery(@RequestParam("question") String question) {

        if(question == null || question.trim().isEmpty()) {
            return new SqlResponse(null, List.of());
        }

        try {
            //schema파일에서 ddl읽기
            String schemaStr = schema.getContentAsString(Charset.defaultCharset());

            //AI에게 자연어 질문과 DDL을 전달해서 sql쿼리 생성
            ChatClient chatClient = ChatClient.builder(chatModel).build();
            String query = chatClient.prompt()
                    .user(userSpec -> userSpec
                            .text(sqlPromptTemplate)
                            .param("question", question)
                            .param("ddl", schemaStr))
                    .call()
                    .content();

            if(query.toLowerCase().startsWith("select")) {
                System.out.println(query);
                return new SqlResponse(query, jdbcTemplate.queryForList(query));
            }

            return new SqlResponse(query, List.of());
        }catch (Exception e) {
            return new SqlResponse("error : " + e.getMessage() , List.of());
        }

    }
}
