package Enum.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AddCohortRequest {
    private String cohortName;
    private String description;
    private String program;
    private MultipartFile file;


}
