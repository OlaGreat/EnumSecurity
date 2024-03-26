package Enum.services;

import Enum.dto.request.AddCohortRequest;

import Enum.dto.response.CohortRegistrationResponse;
import Enum.dto.response.GetCohortResponse;
import Enum.services.cloud.CloudServices;
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
    private CloudServices cloudServices;

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

    private AddCohortRequest buildAddCohortRequest() throws IOException {
        AddCohortRequest request = new AddCohortRequest();
        request.setCohortName("Diamond");
        request.setDescription("A cohort built on ubuntu");
        request.setProgram("Software_Engineering");
        request.setMultipartFile(getAvatar());
        return request;

    }

    private MultipartFile getAvatar() throws IOException {
        Path path = Paths.get(IMAGE_URL);
        InputStream inputStream = Files.newInputStream(path);
        MultipartFile multipartFile = new MockMultipartFile("testImage", inputStream);

        return  multipartFile;


    }


}