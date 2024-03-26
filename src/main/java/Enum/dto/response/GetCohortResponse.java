package Enum.dto.response;

import Enum.data.models.Program;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class GetCohortResponse {

    private String cohortName;
    private String description;
    private Program program;
    private String startDate;
    private String endDate;
    private String  avatarImageUrl;

}
