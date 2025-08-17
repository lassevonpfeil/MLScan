package util;

public class Development {
    static boolean development = false;

    public static String isInDevelopment() {
        if (Development.development) {
            return " (dev version)";
        } else {
            return "";
        }
    }
}
