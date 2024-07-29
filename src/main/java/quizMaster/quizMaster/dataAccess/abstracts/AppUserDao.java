package quizMaster.quizMaster.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import quizMaster.quizMaster.entities.concretes.AppUser;

import java.util.UUID;

public interface AppUserDao extends JpaRepository<AppUser, UUID> {
}
