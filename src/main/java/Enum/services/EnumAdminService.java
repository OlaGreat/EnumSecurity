package Enum.services;


import Enum.data.models.Cohort;
import Enum.data.models.Program;
import Enum.data.repositories.CohortRepository;
import Enum.dto.request.AddCohortRequest;
import Enum.dto.response.CohortRegistrationResponse;
import Enum.dto.response.GetCohortResponse;
import Enum.exceptions.CohortNotFoundException;
import Enum.services.cloud.CloudServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static Enum.dto.response.ResponseMessages.COHORT_ADDED;
import static Enum.exceptions.ExceptionMessages.COHORT_NOT_FOUND;
import static Enum.exceptions.ExceptionMessages.USER_WITH_EMAIL_NOT_FOUND;

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
        cohort.setCohortName(cohortName);
        cohort.setDescription(addCohortRequest.getDescription());
        cohort.setProgram(Program.valueOf(addCohortRequest.getProgram().toUpperCase()));
        cohort.setAvatarImageUrl(cohortAvatarUrl);

        Cohort savedCohort = cohortRepository.save(cohort);

        CohortRegistrationResponse response =new CohortRegistrationResponse();
        response.setMessage(String.format(COHORT_ADDED.getMessage(),cohortName));

        return response;
    }


    @Override
    public List<GetCohortResponse> getAllCohort() {
        List<Cohort> allCohort = cohortRepository.findAll();
        return allCohort.stream()
                .map(EnumAdminService::mapCohort)
                .toList();
    }

    @Override
    public GetCohortResponse getCohort(String cohortName) {
        Cohort cohort = cohortRepository.findCohortByCohortName(cohortName).orElseThrow(
                ()-> new CohortNotFoundException(
                        String.format(COHORT_NOT_FOUND.getMessage(), cohortName))
        );

        return mapCohort(cohort);
    }



    private static GetCohortResponse mapCohort(Cohort cohort){
        GetCohortResponse mappedCohort = GetCohortResponse.builder()
                .cohortName(cohort.getCohortName())
                .description(cohort.getDescription())
                .startDate(cohort.getStartDate())
                .endDate(cohort.getEndDate())
                .avatarImageUrl(cohort.getAvatarImageUrl())
                .program(cohort.getProgram())
                .build();

        return mappedCohort;
    }
}
