package quizMaster.quizMaster.entities.dtos.Request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppleSignInRequestDto {
    private String idToken;
    private String email;
    private String name;
}
