package quizMaster.quizMaster.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import quizMaster.quizMaster.entities.concretes.AppleUser;

import java.util.Optional;
import java.util.UUID;

public interface AppleUserDao extends JpaRepository<AppleUser, UUID> {
    Optional<AppleUser> findByAppleId(String appleId);
}
