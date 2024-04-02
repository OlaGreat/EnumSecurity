package Enum.services;

import Enum.data.models.Role;
import Enum.data.models.User;
import Enum.data.repositories.UserRepository;
import Enum.dto.request.LoginRequest;
import Enum.dto.request.RegisterUserRequest;
import Enum.dto.response.LoginResponse;
import Enum.dto.response.RegisterUserResponse;
import Enum.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static Enum.data.models.Role.CUSTOMER;
import static Enum.dto.response.ResponseMessages.USER_REGISTRATION_SUCCESSFUL;
import static Enum.exceptions.ExceptionMessages.USER_WITH_EMAIL_NOT_FOUND;

@Service
@AllArgsConstructor
public class EnumUserServices implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public RegisterUserResponse signUp(RegisterUserRequest request) {
        String email = request.getEmail().toLowerCase();
        String password = passwordEncoder.encode(request.getPassword());

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(CUSTOMER);

        User savedUser = userRepository.save(user);
        return RegisterUserResponse.builder()
                .message(USER_REGISTRATION_SUCCESSFUL.getMessage())
                .build();
    }

    @Override
    public User getUserByEmail(String userEmail) {
        User user = userRepository.getUserByEmail(userEmail).orElseThrow(()->new UserNotFoundException(
                String.format(USER_WITH_EMAIL_NOT_FOUND.getMessage(), userEmail)));
        return user;
    }


}
