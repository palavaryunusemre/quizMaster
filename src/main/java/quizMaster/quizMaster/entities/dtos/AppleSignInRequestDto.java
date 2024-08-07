package quizMaster.quizMaster.entities.dtos;

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
