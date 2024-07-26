package quizMaster.quizMaster.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quizMaster.quizMaster.entities.concretes.GoogleUser;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface GoogleUserDao extends JpaRepository<GoogleUser, UUID> {

    Optional<GoogleUser> findByGoogleId(String googleId);

    boolean existsByEmail(String email);

    boolean existsByGoogleId(String googleId);
}
