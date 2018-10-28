package org.example.cards.config;

public class GlobalConfig {
    private static String from = "en", to = "en";

    public static void setFrom(String f) {
        from = f;
    }

    public static void setTo(String t) {
        to = t;
    }

    public static String getFrom() {
        return from;
    }

    public static String getTo() {
        return to;
    }
}
