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

@Entity
@Data
@Table(name="apple_users")
@NoArgsConstructor
@AllArgsConstructor
public class AppleUser extends User {
    @NotNull
    @NotBlank
    @Column(name = "username")
    private String name;
    @Column(name="picture_url")
    private String pictureUrl;
    @NotNull
    @NotBlank
    @Column(name = "apple_id")
    private String appleId;

}
