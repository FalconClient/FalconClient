package ir.albino.client;

import net.minecraft.client.main.Main;

import java.io.File;
import java.util.Arrays;

public class Start {
    public static void main(String[] args) {
        // Provide natives
        // Currently supported Linux and Windows

        System.setProperty("org.lwjgl.librarypath", new File("C:/Users/Mmd4j/IdeaProjects/AlbinoClient/test_natives/" + (System.getProperty("os.name").startsWith("Windows") ? "windows" : "linux")).getAbsolutePath());
//        AlbinoClient.prePerform();
        Main.main(concat(new String[]{"--version", "AlbinoClient", "--accessToken", "0", "--assetsDir", "assets", "--assetIndex", "1.8", "--userProperties", "{}", "--illegal-access", "warn"}, args));
    }

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
