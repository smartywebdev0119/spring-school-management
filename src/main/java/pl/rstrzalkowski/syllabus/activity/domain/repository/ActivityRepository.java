package pl.rstrzalkowski.syllabus.activity.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rstrzalkowski.syllabus.activity.domain.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Page<Activity> findByRealisationIdAndArchived(Long realisationId, boolean archived, Pageable pageable);
}