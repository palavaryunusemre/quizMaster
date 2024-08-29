package quizMaster.quizMaster.entities.dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomRequestDto {
    @NotBlank
    @NotNull
    private String roomId;
    @NotBlank
    @NotNull
    private UUID userId;
}
