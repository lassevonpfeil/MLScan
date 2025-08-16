package util;

public class Development {
    static boolean development = true;

    public static String isInDevelopment() {
        if (Development.development == true) {
            return " (dev version)";
        } else {
            return "";
        }
    }
}
