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
@Table(name = "facebook_users")
@NoArgsConstructor
@AllArgsConstructor
public class FacebookUser extends User {
    @NotNull
    @NotBlank
    @Column(name="username")
    private String name;
    @Column(name = "picture_url")
    private String pictureUrl;
    @NotNull
    @NotNull
    @Column(name = "facebook_id")
    private String facebookId;
}
