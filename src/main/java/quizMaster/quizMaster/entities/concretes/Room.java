package quizMaster.quizMaster.entities.concretes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "room_id")
    private String roomId;

    @NotNull
    @NotBlank
    @Column(name = "user_id")
    private UUID userId;

    @CreationTimestamp
    @Column(name = "updated_at")
    private Date updateAt;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
}
