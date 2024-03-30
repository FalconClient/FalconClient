package ir.albino.client.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;

@UtilityClass
public class Common {
    public File getGamePath() {
        if (SystemUtils.IS_OS_WINDOWS)
            return new File(System.getenv("APPDATA") + "/AlbinoClient/");
        return new File("~/AlbinoClient/");
    }
    public File getGameAssetsPath() {
        return new File(getGamePath(),"assets");
    }

    public File getModulesPath() {
        return new File(getGamePath(),"modules");
    }

}
