package Enum.services;

import Enum.data.models.Program;
import Enum.dto.request.AddCohortRequest;

import Enum.dto.request.RegisterUserRequest;
import Enum.dto.response.ApiResponse;
import Enum.dto.response.CohortRegistrationResponse;
import Enum.dto.response.GetAllProgramResponse;
import Enum.dto.response.GetCohortResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static Enum.utils.AppUtils.IMAGE_URL;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class EnumAdminServiceTest {
    @Autowired
    private  AdminService adminService;


    @Test
    public void testAdminCanRegister(){
        RegisterUserRequest request = buildRegisterUserRequest();

        ApiResponse<?> response = adminService.registerAdmin(request);

        assertThat(response).isNotNull();
        System.out.println(response);
    }

    @Test
    public void testAdminCanAddCohort() throws IOException {
        AddCohortRequest addCohortRequest = buildAddCohortRequest();
        CohortRegistrationResponse response = adminService.addCohort(addCohortRequest);
        assertThat(response).isNotNull();

    }

    @Test
    public void testGetCohort(){
        GetCohortResponse response = adminService.getCohort("DIAMOND");
        System.out.println(response);
        assertThat(response).isNotNull();
    }

    @Test
    public void testGetAllCohort(){
        List<GetCohortResponse> allCohort = adminService.getAllCohort();

        System.out.println(allCohort);

        assertThat(allCohort.size()).isGreaterThan(0);

    }

    @Test
    public void createProgram(){
        ApiResponse<?> response = adminService.createProgram("Software Engineering");
        ApiResponse<?> response1 = adminService.createProgram("Java");
        ApiResponse<?> response2 = adminService.createProgram("Python");

        assertThat(response).isNotNull();
    }

    @Test
    public void testGetAllProgram(){
        List<GetAllProgramResponse> programs = adminService.getAllProgram();

        assertThat(programs.size()).isGreaterThan(0);
    }

    private AddCohortRequest buildAddCohortRequest() throws IOException {
        AddCohortRequest request = new AddCohortRequest();
        request.setCohortName("Diamond");
        request.setDescription("A cohort built on ubuntu");
        request.setProgram(List.of( "software Engineering", "python"));
        request.setFile(getAvatar());
        return request;

    }

    private MultipartFile getAvatar() throws IOException {
        Path path = Paths.get(IMAGE_URL);
        InputStream inputStream = Files.newInputStream(path);
        MultipartFile multipartFile = new MockMultipartFile("testImage", inputStream);

        return  multipartFile;
    }

    private RegisterUserRequest buildRegisterUserRequest(){
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("dominic@gmail.com");
        request.setPassword("12345");

        return request;
    }


}