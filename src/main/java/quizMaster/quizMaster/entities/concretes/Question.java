package quizMaster.quizMaster.entities.concretes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import quizMaster.quizMaster.core.enums.Categories;
import quizMaster.quizMaster.core.enums.Options;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questions")
@Inheritance(strategy = InheritanceType.JOINED)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Categories category;

    @NotNull
    @NotBlank
    @Column(name = "question")
    private String question;

    @NotNull
    @NotBlank
    @Column(name = "answer")
    @Enumerated(EnumType.STRING)
    private Options answer;

    @NotNull
    @NotBlank
    @Column(name = "options_a")
    private String optionsA;

    @NotNull
    @NotBlank
    @Column(name = "options_b")
    private String optionsB;

    @NotNull
    @NotBlank
    @Column(name = "options_c")
    private String optionsC;

    @NotNull
    @NotBlank
    @Column(name = "options_d")
    private String optionsD;

    @NotNull
    @NotBlank
    @Column(name = "room_id")
    private String roomId;

    @CreationTimestamp
    @Column(name = "updated_at")
    private Date updateAt;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
}
