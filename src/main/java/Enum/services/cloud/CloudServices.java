package Enum.services.cloud;

import org.springframework.web.multipart.MultipartFile;

public interface CloudServices {
    String upload(MultipartFile file);
}
