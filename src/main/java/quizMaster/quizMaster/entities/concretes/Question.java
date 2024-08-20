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
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "category")
    private Categories category;

    @NotNull
    @NotBlank
    @Column(name = "question")
    private String question;

    @NotNull
    @NotBlank
    @Column(name = "answer")
    private Options answer;

    @NotNull
    @NotBlank
    @Column(name = "options")
    private String options;

    @CreationTimestamp
    @Column(name = "updated_at")
    private Date updateAt;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
}
