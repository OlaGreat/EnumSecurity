package Enum.services;


import Enum.data.models.Cohort;
import Enum.data.models.Program;
import Enum.data.repositories.CohortRepository;
import Enum.dto.request.AddCohortRequest;
import Enum.dto.response.ApiResponse;
import Enum.dto.response.CohortRegistrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static Enum.dto.response.ResponseMessages.COHORT_ADDED;

@Service
@AllArgsConstructor
public class EnumAdminService implements AdminService{

    private final CohortRepository cohortRepository;

    @Override
    public CohortRegistrationResponse addCohort(AddCohortRequest addCohortRequest) {
        String cohortName = addCohortRequest.getCohortName();

        Cohort cohort = new Cohort();
        cohort.setName(cohortName);
        cohort.setDescription(addCohortRequest.getDescription());
        cohort.setProgram(Program.valueOf(addCohortRequest.getProgram().toUpperCase()));

        Cohort savedCohort = cohortRepository.save(cohort);

        CohortRegistrationResponse response =new CohortRegistrationResponse();
        response.setMessage(String.format(COHORT_ADDED.getMessage(),cohortName));

        return response;
    }
}
