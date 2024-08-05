package quizMaster.quizMaster.entities.dtos;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacebookSignInRequestDto {
    private String idToken;
}
