package quizMaster.quizMaster.entities.dtos.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppSignInRequestDto {
    @Email
    @NotNull
    @NotBlank
    private String email;
    @NotBlank
    @NotNull
    private String password;
}
