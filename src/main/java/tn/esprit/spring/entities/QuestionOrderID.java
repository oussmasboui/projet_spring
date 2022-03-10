package tn.esprit.spring.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class QuestionOrderID implements Serializable {
    @ManyToOne
    Quiz quiz;
    @ManyToOne
    Question question;
}
