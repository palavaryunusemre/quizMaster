package quizMaster.quizMaster.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import quizMaster.quizMaster.entities.concretes.FacebookUser;

import java.util.Optional;
import java.util.UUID;

public interface FacebookUserDao extends JpaRepository<FacebookUser, UUID> {
    Optional<FacebookUser> findByFacebookId(String facebookId);
}
