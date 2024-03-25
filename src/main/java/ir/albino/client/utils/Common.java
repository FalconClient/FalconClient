package ir.albino.client.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;

@UtilityClass
public class Common {
    public File getGamePath() {
        String OS = System.getProperty("os.name").toUpperCase();

        if (SystemUtils.IS_OS_WINDOWS)
            return new File(System.getenv("APPDATA") + "/AlbinoClient/");

        return new File("~/AlbinoClient/");
    }
}
