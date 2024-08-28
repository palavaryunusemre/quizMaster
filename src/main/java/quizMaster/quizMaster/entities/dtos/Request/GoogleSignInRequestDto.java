package quizMaster.quizMaster.entities.dtos.Request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GoogleSignInRequestDto {
    private String idToken;
    private String platform;
}
