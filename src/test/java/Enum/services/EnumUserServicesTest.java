package Enum.services;

import Enum.data.models.User;
import Enum.dto.request.RegisterUserRequest;
import Enum.dto.response.RegisterUserResponse;
import Enum.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnumUserServicesTest {
    @Autowired
    private UserService userService;

    @Test
    public void testUserCanSignUp(){
        RegisterUserRequest request = buildUserRegisterRequest();
        RegisterUserResponse response = userService.signUp(request);
        assertThat(response).isNotNull();

    }

    @Test
    public void testFindUserByEmail(){
        String testEmail = "oladipupoolamilekan@gmail.com";
        User user = userService.getUserByEmail(testEmail);
        assertThat(user).isNotNull();
        assertEquals(testEmail, user.getEmail());
    }

    @Test
    public void testThatFindUserByEmailThrowsExceptionWhenGivenUnregisteredEmail(){
        String testEmail = "oladipupo@gmail.com";
        assertThrows(UserNotFoundException.class, ()->userService.getUserByEmail(testEmail));
    }


    @Test
    public void testRegisterAdmin(){
        RegisterUserRequest request = buildRegisterAdmin();
        RegisterUserResponse response = userService.signUp(request);
        assertThat(response).isNotNull();


    }




    private RegisterUserRequest buildUserRegisterRequest(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("oladipupoolamilekan@gmail.com");
        registerUserRequest.setPassword("12345");

        return registerUserRequest;
    }

    private RegisterUserRequest buildRegisterAdmin(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("oladipupoolamilekan55@gmail.com");
        registerUserRequest.setPassword("12345");

        return registerUserRequest;

    }



}