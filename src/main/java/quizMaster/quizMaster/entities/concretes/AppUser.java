package quizMaster.quizMaster.entities.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quizMaster.quizMaster.core.entities.User;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_users")
public class AppUser extends User {
    @NotNull
    @NotBlank
    @Column(name = "username")
    private String username;
    @NotNull
    @NotBlank
    @Column(name = "nickname")
    private String nickname;
    @NotNull
    @NotBlank
    @Column(name = "birthday")
    private Date birthday;
}
