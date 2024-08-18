package quizMaster.quizMaster.core.utilities.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatGPTService {
    @Value("${openai.api.key}")
    private String apiKey;

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final long DELAY_MS = 1000; // 1 saniye gecikme

    public String getChatResponse(String userInput) {
        try {
            Thread.sleep(DELAY_MS); // Her istek öncesi gecikme
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-3.5-turbo");
        body.put("messages", List.of(
                Map.of("role", "system", "content", "You are a helpful assistant."),
                Map.of("role", "user", "content", userInput)
        ));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        Map<String, Object> response = restTemplate.exchange(
                API_URL,
                HttpMethod.POST,
                entity,
                Map.class
        ).getBody();

        return ((Map<String, String>) ((Map<String, Object>) ((List<Object>) response.get("choices")).get(0)).get("message")).get("content");
    }
}