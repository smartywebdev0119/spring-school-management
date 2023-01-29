package pl.rstrzalkowski.syllabus.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "SUBJECT")
public class Subject extends AbstractEntity {
    private String name;

    private String abbreviation;

    private boolean archived;
}