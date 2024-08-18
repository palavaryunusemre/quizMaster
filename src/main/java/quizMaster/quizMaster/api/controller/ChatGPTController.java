package quizMaster.quizMaster.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quizMaster.quizMaster.core.utilities.services.ChatGPTService;

@RestController
@RequestMapping("api/chat")
public class ChatGPTController {
    @Autowired
    private ChatGPTService chatGPTService;

    @PostMapping("/ask")
    public String askChatGPT(@RequestBody String userInput) {
        return chatGPTService.getChatResponse(userInput);
    }
}
