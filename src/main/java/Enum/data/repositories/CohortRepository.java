package Enum.data.repositories;

import Enum.data.models.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CohortRepository extends JpaRepository<Cohort, Long> {
    Optional<Cohort> findCohortByCohortName(String cohortName);
}
