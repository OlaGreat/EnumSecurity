package Enum.utils;

import java.util.ArrayList;
import java.util.List;

public class AppUtils {
    public static String APP_NAME = "Enum";
    public static String ROLES = "roles";
    public static  String SECRET = "Enum";

    public static int SEVEN = 7;

    public static int ONE_YEAR = 365;

    public static String IMAGE_URL = "C:\\Users\\DELL\\Documents\\springProject\\Enum\\src\\test\\resources\\image\\17905085-95d3-45ee-8dd1-f30e8f4c4455.jpg";

    public static List<String> publicPath(){
        return List.of("/api/v1/enum", "/login");
    }

}
