package quizMaster.quizMaster.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import quizMaster.quizMaster.entities.concretes.UserQuestion;

public interface UserQuestionDao extends JpaRepository<UserQuestion, Integer> {

}
