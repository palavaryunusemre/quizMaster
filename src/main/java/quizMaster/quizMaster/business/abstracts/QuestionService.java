package quizMaster.quizMaster.business.abstracts;

import quizMaster.quizMaster.core.utilities.results.Result;
import quizMaster.quizMaster.entities.concretes.Question;
import quizMaster.quizMaster.entities.dtos.Request.ChatGPTRequestDto;

public interface QuestionService {
    Result createQuestion(String roomId, int questionCount);
    Result userCreateQuestion(ChatGPTRequestDto chatGPTRequestDto);
}
