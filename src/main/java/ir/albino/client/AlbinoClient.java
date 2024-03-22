package ir.albino.client;

import ir.albino.client.gui.font.AlbinoFontRenderer;
import org.apache.commons.lang3.SystemUtils;

import java.awt.*;
import java.io.File;

public class AlbinoClient {
    public AlbinoFontRenderer fontRenderer;

    public void initializeFontRenderer() {
        this.fontRenderer = new AlbinoFontRenderer("vazir", 20, Font.PLAIN, true, false);
    }

    public AlbinoClient() {

    }

    public static void prePerform() {
        File gamePath = getGamePath();
        if (!gamePath.exists()) gamePath.mkdirs();
        System.out.println("tes");
    }

    public static File getGamePath() {
        String OS = System.getProperty("os.name").toUpperCase();
        if (SystemUtils.IS_OS_WINDOWS) {
            return new File(System.getenv("APPDATA") + "/AlbinoClient/");
        } else if (SystemUtils.IS_OS_LINUX) {
            return new File("");
        } else return null;
    }
}
