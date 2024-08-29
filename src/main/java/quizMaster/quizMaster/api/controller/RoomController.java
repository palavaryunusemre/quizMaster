package quizMaster.quizMaster.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quizMaster.quizMaster.business.concretes.RoomManager;
import quizMaster.quizMaster.core.utilities.results.Result;
import quizMaster.quizMaster.entities.dtos.Request.RoomRequestDto;

@RestController
@RequestMapping("api/room")
public class RoomController {
    @Autowired
    private RoomManager roomManager;

    @PostMapping("/createRoom")
    public Result createRoom(@RequestBody RoomRequestDto roomRequestDto){
        return roomManager.roomCreate(roomRequestDto);
    }

}
