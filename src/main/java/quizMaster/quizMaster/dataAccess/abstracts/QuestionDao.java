package quizMaster.quizMaster.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quizMaster.quizMaster.entities.concretes.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

}
