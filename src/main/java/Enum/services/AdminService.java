package Enum.services;

import Enum.dto.request.AddCohortRequest;
import Enum.dto.response.CohortRegistrationResponse;
import Enum.dto.response.GetCohortResponse;

import java.util.List;

public interface AdminService {

    CohortRegistrationResponse addCohort(AddCohortRequest addCohortRequest);
    List<GetCohortResponse> getAllCohort();

    GetCohortResponse getCohort(String cohortName);
}
