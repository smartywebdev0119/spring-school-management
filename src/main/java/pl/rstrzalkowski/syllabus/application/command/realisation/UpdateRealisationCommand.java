package pl.rstrzalkowski.syllabus.application.command.realisation;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRealisationCommand {

    @NotNull
    private Long id;
    private Year year;

    private Long teacherId;
}