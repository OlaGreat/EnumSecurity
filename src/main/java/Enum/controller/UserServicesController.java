package Enum.controller;

import Enum.dto.request.LoginRequest;
import Enum.dto.request.RegisterUserRequest;
import Enum.dto.response.LoginResponse;
import Enum.dto.response.RegisterUserResponse;
import Enum.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/enum")
@AllArgsConstructor
public class UserServicesController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest request){
        RegisterUserResponse response = userService.signUp(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }





}
