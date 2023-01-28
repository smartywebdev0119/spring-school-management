package pl.rstrzalkowski.syllabus.grade.infrastructure;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.rstrzalkowski.syllabus.grade.application.GradeCommandHandler;
import pl.rstrzalkowski.syllabus.grade.application.command.ArchiveGradeCommand;
import pl.rstrzalkowski.syllabus.grade.application.command.CreateGradeCommand;
import pl.rstrzalkowski.syllabus.grade.application.command.UpdateGradeCommand;

@RestController
@RequestMapping("/grades")
@RequiredArgsConstructor
public class GradeCommandController {

    private final GradeCommandHandler gradeCommandHandler;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createGrade(@Valid @RequestBody CreateGradeCommand command) {
        gradeCommandHandler.handle(command);
    }

    @PutMapping
    public void updateGrade(@Valid @RequestBody UpdateGradeCommand command) {
        gradeCommandHandler.handle(command);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void archiveById(@PathVariable("id") Long id) {
        gradeCommandHandler.handle(new ArchiveGradeCommand(id));
    }
}
