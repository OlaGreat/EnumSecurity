package Enum.data.repositories;

import Enum.data.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    Optional<Program> getProgramByProgramName(String programName);

}
