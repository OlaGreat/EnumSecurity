package Enum.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AddCohortRequest {
    private String cohortName;
    private String description;
    private List<String> program;
    private MultipartFile file;
    private String startDate;
    private String endDate;




}
