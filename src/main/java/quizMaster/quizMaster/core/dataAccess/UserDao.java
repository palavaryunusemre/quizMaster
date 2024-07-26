package quizMaster.quizMaster.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import quizMaster.quizMaster.core.entities.User;
import quizMaster.quizMaster.core.utilities.results.DataResult;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

}
