package quizMaster.quizMaster.entities.dtos.Request;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacebookSignInRequestDto {
    private String idToken;
}
