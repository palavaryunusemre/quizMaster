package quizMaster.quizMaster.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quizMaster.quizMaster.entities.concretes.Room;

@Repository
public interface RoomDao extends JpaRepository<Room,Long> {
}
