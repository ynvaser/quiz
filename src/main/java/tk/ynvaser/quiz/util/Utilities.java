package tk.ynvaser.quiz.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utilities {
    public static ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
