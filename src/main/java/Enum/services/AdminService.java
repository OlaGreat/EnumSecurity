package Enum.services;

import Enum.data.models.Program;
import Enum.dto.request.AddCohortRequest;
import Enum.dto.request.RegisterUserRequest;
import Enum.dto.response.ApiResponse;
import Enum.dto.response.CohortRegistrationResponse;
import Enum.dto.response.GetAllProgramResponse;
import Enum.dto.response.GetCohortResponse;

import java.util.List;

public interface AdminService {

    CohortRegistrationResponse addCohort(AddCohortRequest addCohortRequest);
    List<GetCohortResponse> getAllCohort();

    GetCohortResponse getCohort(String cohortName);

    ApiResponse<?> registerAdmin(RegisterUserRequest request);

    ApiResponse<?> createProgram(String programName);

    List<GetAllProgramResponse> getAllProgram();

    Program findProgramByName(String programName);
}
