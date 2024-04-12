package dev.slangware.ultralight.util;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class HTMLConvertor {
    private static final Map<String, String> COLORS = new HashMap<>();
    private static final Map<String, String> STYLES = new HashMap<>();

    static {
        COLORS.put("0", "color:#000000");
        COLORS.put("1", "color:#0000AA");
        COLORS.put("2", "color:#00AA00");
        COLORS.put("3", "color:#00AAAA");
        COLORS.put("4", "color:#AA0000");
        COLORS.put("5", "color:#AA00AA");
        COLORS.put("6", "color:#FFAA00");
        COLORS.put("7", "color:#AAAAAA");
        COLORS.put("8", "color:#555555");
        COLORS.put("9", "color:#5555FF");
        COLORS.put("a", "color:#55FF55");
        COLORS.put("r", "color:#FFFFFF");
        COLORS.put("b", "color:#55FFFF");
        COLORS.put("c", "color:#FF5555");
        COLORS.put("d", "color:#FF55FF");
        COLORS.put("e", "color:#FFFF55");
        COLORS.put("f", "color:#FFFFFF");

        STYLES.put("k", "font-weight:obfuscated");
        STYLES.put("l", "font-weight:bold");
        STYLES.put("m", "text-decoration:line-through");
        STYLES.put("n", "text-decoration:underline");
        STYLES.put("o", "font-style:italic");
    }


    /**
     * Converts a minecraft given text to an HTML component.
     *
     * @param text The text to be converted.
     * @return The HTML component representing the text.
     */
    public static String toHtmlComponent(String text) {
        // Replace special characters with their corresponding values
        String sanitizedObject = text.replace("\\u00A7", "§")
                .replace("\n", "</br>");

        // Initialize variables
        Map<String, String> currentSection = new HashMap<>();
        currentSection.put("style", "");
        currentSection.put("text", "");
        StringBuilder builder = new StringBuilder();
        char[] chars = sanitizedObject.toCharArray();
        int i = 0;

        // Iterate through each character in the sanitized text
        while (i < chars.length) {
            char c = chars[i];
            if ((c == '&' || c == '§') && chars.length > i + 1) {
                // Check for colors
                char nextChar = chars[++i];
                if (COLORS.containsKey(String.valueOf(nextChar))) {
                    if (!currentSection.get("text").isEmpty()) {
                        // Add previously collected text as a span element
                        String styles = currentSection.get("style");
                        String builtText = "<span style=\"white-space: pre" + (styles.isEmpty() ? "" : "; " + styles) + "\">" + currentSection.get("text") + "</span>";
                        builder.append(builtText);
                        currentSection.put("style", "");
                        currentSection.put("text", "");
                    }
                    if (!currentSection.get("style").isEmpty()) {
                        currentSection.put("style", "");
                    }
                    // Set the current section's style
                    currentSection.put("style", COLORS.get(String.valueOf(nextChar)));
                    i++;
                    continue;
                }

                // Check for styles
                if (STYLES.containsKey(String.valueOf(nextChar))) {
                    if (i + 1 == chars.length) break;
                    // Add the style to the current section
                    String s = currentSection.get("style");
                    currentSection.put("style", String.format("%s%s", s != null && !s.isEmpty() ? (s + "; ") : s, STYLES.get(String.valueOf(nextChar))));
                    i++;
                    continue;
                }

                // Append the characters to the current section's text
                currentSection.put("text", currentSection.get("text") + chars[i - 1]);
                currentSection.put("text", currentSection.get("text") + nextChar);
                i++;
                continue;
            }
            // Append the character to the current section's text
            currentSection.put("text", currentSection.get("text") + c);
            i++;
        }
        if (!currentSection.get("text").isEmpty()) {
            // Add the last collected text as a span element
            String styles = currentSection.get("style");
            String builtText = "<span style=\"white-space: pre" + (styles.isEmpty() ? "" : "; " + styles) + "\">" + currentSection.get("text") + "</span>";
            builder.append(builtText);
        }
        // Return the final HTML component as a string
        return builder.toString();
    }
}
