package Enum.services;


import Enum.data.models.Cohort;
import Enum.data.models.Program;
import Enum.data.models.User;
import Enum.data.repositories.CohortRepository;
import Enum.data.repositories.ProgramRepository;
import Enum.data.repositories.UserRepository;
import Enum.dto.request.AddCohortRequest;
import Enum.dto.request.RegisterUserRequest;
import Enum.dto.response.ApiResponse;
import Enum.dto.response.CohortRegistrationResponse;
import Enum.dto.response.GetAllProgramResponse;
import Enum.dto.response.GetCohortResponse;
import Enum.exceptions.CohortNotFoundException;
import Enum.exceptions.ProgramNotFound;
import Enum.services.cloud.CloudServices;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static Enum.data.models.Role.ADMIN;
import static Enum.dto.response.ResponseMessages.COHORT_ADDED;
import static Enum.dto.response.ResponseMessages.USER_REGISTRATION_SUCCESSFUL;
import static Enum.exceptions.ExceptionMessages.*;

@Service
@AllArgsConstructor
public class EnumAdminService implements AdminService{

    private final CohortRepository cohortRepository;
    private final CloudServices cloudServices;
    private final UserRepository userRepository;

    private final ProgramRepository programRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public CohortRegistrationResponse addCohort(AddCohortRequest addCohortRequest) {
//        if (programRepository.existsByProgramName());
        String cohortName = addCohortRequest.getCohortName();
        MultipartFile cohortAvatar = addCohortRequest.getFile();
        String cohortAvatarUrl = cloudServices.upload(cohortAvatar);
        List<Program> programs = mapProgram(addCohortRequest.getProgram());


        Cohort cohort = new Cohort();
        cohort.setCohortName(cohortName);
        cohort.setDescription(addCohortRequest.getDescription());
        cohort.setPrograms(programs);
        cohort.setAvatarImageUrl(cohortAvatarUrl);
        cohort.setStartDate(addCohortRequest.getStartDate());
        cohort.setEndDate(addCohortRequest.getEndDate());

        System.out.println(cohort.getPrograms());

        Cohort savedCohort = cohortRepository.save(cohort);

        CohortRegistrationResponse response =new CohortRegistrationResponse();
        response.setMessage(String.format(COHORT_ADDED.getMessage(),cohortName));

        return response;
    }


    @Override
    public List<GetCohortResponse> getAllCohort() {
        List<Cohort> allCohort = cohortRepository.findAll();
        List<GetCohortResponse> allCohorts = allCohort.stream()
                .map(EnumAdminService::mapCohort)
                .toList();

        return allCohorts;
    }

    @Override
    public GetCohortResponse getCohort(String cohortName) {
        Cohort cohort = cohortRepository.findCohortByCohortName(cohortName).orElseThrow(
                ()-> new CohortNotFoundException(
                        String.format(COHORT_NOT_FOUND.getMessage(), cohortName))
        );

        return mapCohort(cohort);
    }

    @Override
    public ApiResponse<?> registerAdmin(RegisterUserRequest request) {

        String password = passwordEncoder.encode(request.getPassword());

        User user = new User();
        user.setRole(ADMIN);
        user.setEmail(request.getEmail().toLowerCase());
        user.setPassword(password);
        User savedUser = userRepository.save(user);

        return ApiResponse.builder().data(USER_REGISTRATION_SUCCESSFUL.getMessage()).build();
    }

    @Override
    public ApiResponse<?> createProgram(String programName) {
        Program program = new Program();
        program.setProgramName(programName);
        Program savedProgram = programRepository.save(program);

        return ApiResponse.builder()
                .data(COHORT_ADDED.getMessage())
                .build();
    }

    @Override
    public List<GetAllProgramResponse> getAllProgram() {
        List<Program> programs = programRepository.findAll();

        return  programs.stream()
                .map(EnumAdminService::mapProgram)
                .toList();

    }

    private static GetAllProgramResponse mapProgram(Program program){
        return GetAllProgramResponse.builder()
                .programName(program.getProgramName())
                .build();
    }


    private static GetCohortResponse mapCohort(Cohort cohort){
        GetCohortResponse mappedCohort = GetCohortResponse.builder()
                .cohortName(cohort.getCohortName())
                .description(cohort.getDescription())
                .startDate(cohort.getStartDate())
                .endDate(cohort.getEndDate())
                .avatarImageUrl(cohort.getAvatarImageUrl())
                .programs(cohort.getPrograms())
                .build();

        return mappedCohort;
    }

    public Program findProgramByName(String programName) {
        Program foundProgram = programRepository.getProgramByProgramName(programName).orElseThrow(
                () -> new ProgramNotFound(PROGRAM_NOT_FOUND.getMessage()));

        return foundProgram;
    }

    private  List<Program> mapProgram(List<String> program){
        System.out.println("------------------------------------------------>>>>>>>>>>>>>>>>>>>>");

        List<Program> foundPrograms = new ArrayList<>();
        for (String s : program) {
            Program foundProgram = findProgramByName(s);
            foundPrograms.add(foundProgram);
            System.out.println(foundProgram);
        }

       return foundPrograms;
    }


}
