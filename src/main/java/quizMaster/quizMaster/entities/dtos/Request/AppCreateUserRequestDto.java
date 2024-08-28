package quizMaster.quizMaster.entities.dtos.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppCreateUserRequestDto {
    @Email
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private String userName;
    @NotNull
    @NotBlank
    private String nickName;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull
    private LocalDate birthday;
}
