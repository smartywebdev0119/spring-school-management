package pl.rstrzalkowski.syllabus.domain.service.level;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.application.command.level.ArchiveLevelCommand;
import pl.rstrzalkowski.syllabus.application.command.level.CreateLevelCommand;
import pl.rstrzalkowski.syllabus.application.command.level.UpdateLevelCommand;
import pl.rstrzalkowski.syllabus.domain.exception.level.LevelAlreadyExistsException;
import pl.rstrzalkowski.syllabus.domain.exception.level.LevelNotFoundException;
import pl.rstrzalkowski.syllabus.domain.model.Level;
import pl.rstrzalkowski.syllabus.domain.repository.LevelRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class LevelCommandService {

    private final LevelRepository levelRepository;


    public void create(CreateLevelCommand command) {
        Level level = new Level();
        level.setLevel(command.getLevel());

        if (levelRepository.findByLevel(level.getLevel()).isPresent()) {
            throw new LevelAlreadyExistsException();
        }

        levelRepository.save(level);
    }

    public void update(UpdateLevelCommand command) {
        Level level = levelRepository.findById(command.getId())
                .orElseThrow(LevelNotFoundException::new);

        if (Objects.equals(level.getLevel(), command.getLevel())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        level.setLevel(command.getLevel());
        levelRepository.save(level);
    }

    public void archiveById(ArchiveLevelCommand command) {
        Level level = levelRepository.findById(command.id())
                .orElseThrow(LevelNotFoundException::new);

        level.setArchived(true);
        levelRepository.save(level);
    }
}