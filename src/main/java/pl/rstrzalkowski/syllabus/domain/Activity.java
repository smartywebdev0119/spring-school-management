package pl.rstrzalkowski.syllabus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rstrzalkowski.syllabus.realisation.domain.model.Realisation;
import pl.rstrzalkowski.syllabus.user.domain.model.User;

@Entity
@Getter
@Setter
@Table(name = "ACTIVITY")
@NoArgsConstructor
public class Activity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Realisation realisation;

    @ManyToOne
    private User teacher;

    private String name;

    private Integer weight;

    private String description;
}
