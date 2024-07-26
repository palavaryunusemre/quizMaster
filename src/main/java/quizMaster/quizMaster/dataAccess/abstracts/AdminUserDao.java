package quizMaster.quizMaster.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quizMaster.quizMaster.entities.concretes.AdminUser;

import java.util.UUID;

@Repository
public interface AdminUserDao extends JpaRepository<AdminUser, UUID> {
    @Query("From AdminUser Where email=:email")
    AdminUser findByEmail(String email);
}
