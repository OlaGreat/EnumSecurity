package Enum.services.cloud;

import Enum.utils.AppUtils;
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

import static Enum.utils.AppUtils.IMAGE_URL;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CloudinaryTest {
    @Autowired
    private CloudServices cloudServices;

    @Test
    public void testCloudinaryUpload() throws IOException {
        Path path = Paths.get(IMAGE_URL);

        InputStream inputStream = Files.newInputStream(path);
        MultipartFile file = new MockMultipartFile("testImage", inputStream);
        String response = cloudServices.upload(file);
        System.out.println(response);
        assertNotNull(response);
    }

}