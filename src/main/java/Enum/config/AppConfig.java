package Enum.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${CLOUDINARY_NAME}")
    private String cloudName;

    @Value("${CLOUDINARY_KEY}")
    private String cloudKey;

    @Value("${CLOUDINARY_SECRET}")
    private String cloudSecret;


    public String getCloudName() {
        return cloudName;
    }

    public String getCloudKey() {
        return cloudKey;
    }

    public String getCloudSecret() {
        return cloudSecret;
    }
}
