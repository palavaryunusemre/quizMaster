package quizMaster.quizMaster.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quizMaster.quizMaster.business.concretes.QuestionManager;
import quizMaster.quizMaster.core.utilities.results.Result;
import quizMaster.quizMaster.entities.dtos.Request.ChatGPTRequestDto;

@RestController
@RequestMapping("api/chat")
public class ChatGPTController {
    @Autowired
    private QuestionManager questionManager;

    @PostMapping("/ask")
    public Result askChatGPT(@RequestBody ChatGPTRequestDto chatGPTRequestDto) {
        String roomId = chatGPTRequestDto.getRoomId();
        int questionCount = chatGPTRequestDto.getQuestionCount();
        return questionManager.createQuestion(roomId, questionCount);
    }
    @PostMapping("/createQuestion")
    public Result createQuestion(@RequestBody ChatGPTRequestDto chatGPTRequestDto) {
        return questionManager.userCreateQuestion(chatGPTRequestDto);
    }
}
