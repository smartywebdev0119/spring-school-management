package pl.rstrzalkowski.syllabus.subject.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.subject.domain.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Page<Subject> findAllByArchived(boolean archived, Pageable pageable);

    Page<Subject> findSubjectByNameContainingIgnoreCase(String name, Pageable pageable);

}
