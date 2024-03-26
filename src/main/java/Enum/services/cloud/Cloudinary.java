package Enum.services.cloud;


import Enum.config.AppConfig;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class Cloudinary implements CloudServices {
    private final AppConfig appConfig;
    @Override
    public String upload(MultipartFile file) {
        com.cloudinary.Cloudinary cloudinary = new com.cloudinary.Cloudinary();
        Uploader uploader = cloudinary.uploader();

        try {
            Map<?,?> response = uploader.upload(file.getBytes(), ObjectUtils.asMap(
                    "public_id","enumCohortAvatars/cohort/avatars"+ UUID.randomUUID(),
                    "api_key", appConfig.getCloudKey(),
                    "api_secret", appConfig.getCloudSecret(),
                    "cloud_name", appConfig.getCloudName(),
                    "secure", true
            ));
            return response.get("url").toString();
        }catch (IOException exception){
            throw new RuntimeException("File upload failed");
        }
    }
}
