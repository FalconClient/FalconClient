package ir.albino.client;

import com.fasterxml.jackson.databind.json.JsonMapper;
import ir.albino.client.account.AltManager;
import net.minecraft.client.main.Main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class Start {
    public static void main(String[] args) throws IOException {
        System.setProperty("org.lwjgl.librarypath", new File("C:/Users/Mmd4j/IdeaProjects/AlbinoClient/test_natives/" + (System.getProperty("os.name").startsWith("Windows") ? "windows" : "linux")).getAbsolutePath());
        AlbinoClient.prePerform();
        String playerUserName = "AlbinoPlayer";
        String uuid = UUID.randomUUID().toString();
        File file = new File(AlbinoClient.getGamePath(), "users.json");
        AltManager manager = new AltManager();
        if (file.exists()) {
            manager = new JsonMapper().readValue(file, AltManager.class);
            playerUserName = manager.currentSession.getUsername();
            uuid = manager.currentSession.getPlayerID();
        }
        AltManager.setInstance(manager);

        Main.main(concat(new String[]{"--version", "AlbinoClient", "--accessToken", "0", "--assetsDir", "assets", "--assetIndex", "1.8", "--userProperties", "{}"
                , "--username", playerUserName, "--uuid", uuid}, args));
    }

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
