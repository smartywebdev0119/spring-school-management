package pl.rstrzalkowski.syllabus.domain.service.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rstrzalkowski.syllabus.application.command.subject.ArchiveSubjectCommand;
import pl.rstrzalkowski.syllabus.application.command.subject.CreateSubjectCommand;
import pl.rstrzalkowski.syllabus.application.command.subject.UpdateSubjectCommand;
import pl.rstrzalkowski.syllabus.domain.exception.subject.SubjectNotFoundException;
import pl.rstrzalkowski.syllabus.domain.exception.subject.SubjectUpdateException;
import pl.rstrzalkowski.syllabus.domain.model.Grade;
import pl.rstrzalkowski.syllabus.domain.model.Realisation;
import pl.rstrzalkowski.syllabus.domain.model.Subject;
import pl.rstrzalkowski.syllabus.infrastructure.repository.GradeRepository;
import pl.rstrzalkowski.syllabus.infrastructure.repository.RealisationRepository;
import pl.rstrzalkowski.syllabus.infrastructure.repository.SubjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectCommandService {

    private final SubjectRepository subjectRepository;
    private final RealisationRepository realisationRepository;
    private final GradeRepository gradeRepository;


    public void create(CreateSubjectCommand command) {
        Subject subject = new Subject();
        subject.setName(command.getName());
        subject.setAbbreviation(command.getAbbreviation());

        subjectRepository.save(subject);
    }


    public void archiveById(ArchiveSubjectCommand command) {
        Subject subject = subjectRepository.findById(command.id())
                .orElseThrow(SubjectNotFoundException::new);

        if (subject.isArchived()) {
            return;
        }

        List<Realisation> realisations = realisationRepository.findByArchivedAndSubjectId(false, subject.getId());
        realisations.forEach(realisation -> {
            realisation.getPosts().forEach(post -> post.setArchived(true));
            realisation.getActivities().forEach(activity -> {
                List<Grade> grades = gradeRepository.findByActivityIdAndArchived(activity.getId(), false);
                grades.forEach((grade -> grade.setArchived(true)));
                activity.setArchived(true);
            });
            realisation.setArchived(true);
        });

        subject.setArchived(true);
        subjectRepository.save(subject);
    }


    public void update(UpdateSubjectCommand command) {
        Subject subject = subjectRepository.findById(command.getId())
                .orElseThrow(SubjectNotFoundException::new);

        subject.setName(command.getName() == null ? subject.getName() : command.getName());
        subject.setAbbreviation(command.getAbbreviation() == null ? subject.getAbbreviation() : command.getAbbreviation());

        try {
            subjectRepository.save(subject);
        } catch (Exception e) {
            throw new SubjectUpdateException();
        }
    }
}
