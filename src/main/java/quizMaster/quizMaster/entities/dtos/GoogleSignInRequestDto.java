package quizMaster.quizMaster.entities.dtos;

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
