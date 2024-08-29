package quizMaster.quizMaster.business.abstracts;

import quizMaster.quizMaster.core.utilities.results.Result;
import quizMaster.quizMaster.entities.dtos.Request.RoomRequestDto;

import java.util.UUID;

public interface RoomService {
    Result roomCreate(RoomRequestDto roomRequestDto);
}
