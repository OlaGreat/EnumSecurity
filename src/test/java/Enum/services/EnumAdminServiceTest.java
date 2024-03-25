package Enum.services;

import Enum.dto.request.AddCohortRequest;
import Enum.dto.response.ApiResponse;
import Enum.dto.response.CohortRegistrationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnumAdminServiceTest {
    @Autowired
    private  AdminService adminService;

    @Test
    public void testAdminCanAddCohort(){
        AddCohortRequest addCohortRequest = buildAddCohortRequest();
        CohortRegistrationResponse response = adminService.addCohort(addCohortRequest);
        assertThat(response).isNotNull();

    }

    private AddCohortRequest buildAddCohortRequest(){
        AddCohortRequest request = new AddCohortRequest();
        request.setCohortName("Diamond");
        request.setDescription("A cohort built on ubuntu");
        request.setProgram("Software_Engineering");
        return request;

    }

}