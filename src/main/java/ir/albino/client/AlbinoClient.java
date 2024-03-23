package ir.albino.client;

import com.cosium.matrix_communication_client.MatrixResources;
import ir.albino.client.gui.font.AlbinoFontRenderer;
import net.minecraft.util.Matrix4f;
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

    public static String prePerform() {
        File gamePath = getGamePath();
        if (!gamePath.exists()) {
            gamePath.mkdirs();
            File nativePath = new File(gamePath, "natives");
            nativePath.mkdir();
            if (SystemUtils.IS_OS_WINDOWS) {

            } else {

            }
            System.out.println(String.format("Created the game folder in %s", gamePath));
        }
        return "";
    }

    public static File getGamePath() {
        String OS = System.getProperty("os.name").toUpperCase();
        if (SystemUtils.IS_OS_WINDOWS)
            return new File(System.getenv("APPDATA") + "/AlbinoClient/");
        else {
            return new File("~/AlbinoClient/");
        }
    }
}
