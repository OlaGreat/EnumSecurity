package Enum.controller;

import Enum.data.models.Program;
import Enum.dto.request.AddCohortRequest;
import Enum.dto.request.RegisterUserRequest;
import Enum.dto.response.CohortRegistrationResponse;
import Enum.dto.response.GetAllProgramResponse;
import Enum.dto.response.GetCohortResponse;
import Enum.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/admin")
@CrossOrigin("*")

public class AdminController {
    private final AdminService adminService;



    @GetMapping("/get-all-cohort")
    public ResponseEntity<List<GetCohortResponse>> getAllCohort(){
        List<GetCohortResponse> allCohort = adminService.getAllCohort();
        return ResponseEntity.status(HttpStatus.OK).body(allCohort);
    }


    @PostMapping("/add-cohort")
    public ResponseEntity<?>addCohort(@ModelAttribute AddCohortRequest request){
        CohortRegistrationResponse response = adminService.addCohort(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/get-all-programs")
    public ResponseEntity<List<GetAllProgramResponse>> getAllProgram(){
        List<GetAllProgramResponse> programs = adminService.getAllProgram();
        return ResponseEntity.status(HttpStatus.OK).body(programs);
    }


}
