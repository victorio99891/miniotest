package pl.wiktor.minioapi.utils;

public class ObjectPathExtractor {
    private ObjectPathExtractor() {
    }

    public static String extractPath(String url, String delimiter) {
        String[] split = url.split(delimiter);
        if (split.length <= 1) {
            return "";
        }
//        return split[1].endsWith("/") ? split[1] : split[1].concat("/");
        return split[1];
    }

    private static boolean isFile(String path) {
        String[] split = path.split(".");
//        if(split.)
        return true;
    }
}
