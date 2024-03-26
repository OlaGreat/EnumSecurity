package Enum.services;

import Enum.data.models.User;
import Enum.dto.request.LoginRequest;
import Enum.dto.request.RegisterUserRequest;
import Enum.dto.response.LoginResponse;
import Enum.dto.response.RegisterUserResponse;
import org.springframework.stereotype.Service;


public interface UserService {

    RegisterUserResponse signUp(RegisterUserRequest request);
    User getUserByEmail(String UserEmail);



}
