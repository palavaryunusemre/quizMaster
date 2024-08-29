package quizMaster.quizMaster.business.concretes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quizMaster.quizMaster.business.abstracts.RoomService;
import quizMaster.quizMaster.core.dataAccess.UserDao;
import quizMaster.quizMaster.core.entities.User;
import quizMaster.quizMaster.core.utilities.results.Result;
import quizMaster.quizMaster.dataAccess.abstracts.RoomDao;
import quizMaster.quizMaster.entities.concretes.Room;
import quizMaster.quizMaster.entities.dtos.Request.RoomRequestDto;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomManager implements RoomService {
    private final RoomDao roomDao;
    private final UserDao userDao;
    @Override
    public Result roomCreate(RoomRequestDto roomRequestDto) {
        Room room = new Room();
        User user = userDao.getUserById(roomRequestDto.getUserId());
        if(user == null) {
            return new Result(false, 404);
        }
        room.setUserId(roomRequestDto.getUserId());
        room.setRoomId(roomRequestDto.getRoomId());
        roomDao.save(room);
        return new Result(true,200);
    }
}
