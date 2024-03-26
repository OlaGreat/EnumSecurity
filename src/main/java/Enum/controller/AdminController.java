package Enum.controller;

import Enum.dto.request.AddCohortRequest;
import Enum.dto.response.CohortRegistrationResponse;
import Enum.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/admin")
@CrossOrigin("*")

public class AdminController {
    private final AdminService adminService;
    @PostMapping
    public String welcome(){
        return "welcom";
    }

    @PostMapping("/add-cohort")
    public ResponseEntity<?>addCohort(@RequestBody AddCohortRequest request){
        CohortRegistrationResponse response = adminService.addCohort(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
