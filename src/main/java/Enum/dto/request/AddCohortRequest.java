package Enum.dto.request;

import Enum.data.models.Program;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AddCohortRequest {
    private String cohortName;
    private String description;
    private String program;
    private MultipartFile multipartFile;


}
