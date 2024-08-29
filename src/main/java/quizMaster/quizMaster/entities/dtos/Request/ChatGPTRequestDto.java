package quizMaster.quizMaster.entities.dtos.Request;

import lombok.*;
import quizMaster.quizMaster.core.enums.Categories;
import quizMaster.quizMaster.core.enums.Options;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatGPTRequestDto {
    private String roomId;
    private int questionCount;
    private UUID userId;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private Options answer;
    private Categories categories;
}
