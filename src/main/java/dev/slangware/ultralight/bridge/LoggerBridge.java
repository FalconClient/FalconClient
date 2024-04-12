// UltraLight Reborn - https://github.com/Janrupf/ultralight-java-reborn/tree/main/examples/glfw
package dev.slangware.ultralight.bridge;

import dev.slangware.ultralight.UltraManager;
import net.janrupf.ujr.api.logger.UltralightLogLevel;
import net.janrupf.ujr.api.logger.UltralightLogger;
import org.apache.logging.log4j.Level;

public class LoggerBridge implements UltralightLogger {
    /**
     * Logs a message with the specified log level.
     *
     * @param logLevel The log level.
     * @param message The message to log.
     */
    @Override
    public void logMessage(UltralightLogLevel logLevel, String message) {
        // Translate the log level to the corresponding Level object
        Level translatedLevel = translateLogLevel(logLevel);

        // Log the message using the translated log level
        UltraManager.getLogger().log(translatedLevel, message);
    }

    /**
     * Translates the given UltralightLogLevel to a Level object.
     * @param logLevel the UltralightLogLevel to be translated
     * @return the corresponding Level object
     */
    private Level translateLogLevel(UltralightLogLevel logLevel) {
        // Use a switch statement to match the given UltralightLogLevel
        switch (logLevel) {
            // If the log level is ERROR, return the Level.ERROR object
            case ERROR:
                return Level.ERROR;
            // If the log level is WARNING, return the Level.WARN object
            case WARNING:
                return Level.WARN;
            // If the log level is INFO, return the Level.INFO object
            case INFO:
                return Level.INFO;
        }

        // If the log level is not ERROR, WARNING, or INFO, return the Level.OFF object
        return Level.OFF;
    }
}
