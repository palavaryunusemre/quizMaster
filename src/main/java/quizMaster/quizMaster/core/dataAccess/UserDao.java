package quizMaster.quizMaster.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import quizMaster.quizMaster.core.entities.User;
import quizMaster.quizMaster.core.utilities.results.DataResult;

import java.util.Optional;
import java.util.UUID;

public interface UserDao extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    @Query("From User Where id = :userId")
    User getUserById(UUID userId);

}
