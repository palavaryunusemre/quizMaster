package quizMaster.quizMaster.business.abstracts;

import quizMaster.quizMaster.core.utilities.results.Result;
import quizMaster.quizMaster.entities.concretes.Question;

public interface QuestionService {
    Result createQuestion(String roomId, int questionCount);
}
