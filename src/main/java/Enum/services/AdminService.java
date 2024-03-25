package Enum.services;

import Enum.dto.request.AddCohortRequest;
import Enum.dto.response.CohortRegistrationResponse;

public interface AdminService {

    CohortRegistrationResponse addCohort(AddCohortRequest addCohortRequest);
}
