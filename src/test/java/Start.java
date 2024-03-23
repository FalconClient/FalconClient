import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import com.fasterxml.jackson.databind.json.JsonMapper;
import ir.albino.client.AlbinoClient;
import ir.albino.client.account.AltManager;
import net.minecraft.client.main.Main;

public class Start {
    public static void main(String[] args) throws IOException {
        // Provide natives
        // Currently supported Linux and Windows
        String playerUserName = "AlbinoPlayer";
        String uuid = null;
        File file = new File(AlbinoClient.getGamePath(), "users");
        if (file.exists()) {
            AltManager manager = new JsonMapper().readValue(file, AltManager.class);
            playerUserName = manager.currentSession.getUsername();
            uuid = manager.currentSession.getPlayerID();
        }
        System.setProperty("org.lwjgl.librarypath", new File("./test_natives/" + (System.getProperty("os.name").startsWith("Windows") ? "windows" : "linux")).getAbsolutePath());
        Main.main(concat(new String[]{"--version", "AlbinoClient", "--accessToken", "0", "--assetsDir", "assets", "--assetIndex", "1.8", "--userProperties", "{}", "--illegal-access", "warn"
                , "username", playerUserName, "uuid", uuid}, args));
    }

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
