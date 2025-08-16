package util;

public class Development {
    static boolean development = true;

    public static String isInDevelopment() {
        if (Development.development) {
            return " (dev version)";
        } else {
            return "";
        }
    }
}
