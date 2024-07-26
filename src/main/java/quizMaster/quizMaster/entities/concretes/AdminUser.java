package quizMaster.quizMaster.entities.concretes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import quizMaster.quizMaster.core.entities.User;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="admin_users")
public class AdminUser extends User {
    @NotBlank
    @NotNull
    @Column(name="username")
    private String userName;
}
