package quizMaster.quizMaster.entities.dtos.Request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatGPTRequestDto {
    private String roomId;
    private int questionCount;
}
