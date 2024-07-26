package quizMaster.quizMaster.entities.concretes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quizMaster.quizMaster.core.entities.User;
import quizMaster.quizMaster.core.enums.Roles;

@Entity
@Table(name = "google_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoogleUser extends User {
    @NotNull
    @NotBlank
    @Column(name="username")
    private String name;

    @Column(name = "picture_url")
    private String pictureUrl;

    @NotNull
    @NotNull
    @Column(name = "google_id")
    private String googleId;

    @NotNull
    @NotBlank
    @Column(name="platform")
    private String platform;
}
