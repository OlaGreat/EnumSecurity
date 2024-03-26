package Enum.services;


import Enum.data.models.Cohort;
import Enum.data.models.Program;
import Enum.data.repositories.CohortRepository;
import Enum.dto.request.AddCohortRequest;
import Enum.dto.response.ApiResponse;
import Enum.dto.response.CohortRegistrationResponse;
import Enum.services.cloud.CloudServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static Enum.dto.response.ResponseMessages.COHORT_ADDED;

@Service
@AllArgsConstructor
public class EnumAdminService implements AdminService{

    private final CohortRepository cohortRepository;
    private final CloudServices cloudServices;

    @Override
    public CohortRegistrationResponse addCohort(AddCohortRequest addCohortRequest) {
        String cohortName = addCohortRequest.getCohortName();
        MultipartFile cohortAvatar = addCohortRequest.getMultipartFile();
        String cohortAvatarUrl = cloudServices.upload(cohortAvatar);

        Cohort cohort = new Cohort();
        cohort.setName(cohortName);
        cohort.setDescription(addCohortRequest.getDescription());
        cohort.setProgram(Program.valueOf(addCohortRequest.getProgram().toUpperCase()));
        cohort.setAvatarImageUrl(cohortAvatarUrl);

        Cohort savedCohort = cohortRepository.save(cohort);

        CohortRegistrationResponse response =new CohortRegistrationResponse();
        response.setMessage(String.format(COHORT_ADDED.getMessage(),cohortName));

        return response;
    }
}
