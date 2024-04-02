package ir.albino.client.features;

import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.pathfinding.Path;

import java.io.File;
import java.time.Instant;

public class DiscordRPC extends Thread {
    private final Core core;
    private final Activity activity;

    public DiscordRPC() {
        System.loadLibrary("windows-amd64-discord_game_sdk_jni");
        System.loadLibrary("discord_game_sdk");
        CreateParams params = new CreateParams();
        params.setClientID(1221159378724589629L);
        params.setFlags(CreateParams.getDefaultFlags());
        core = new Core(params);
        activity = new Activity();
        activity.assets().setLargeImage("game_icon");
        activity.assets().setLargeText("Albino Client");
        activity.setDetails("اولین کلاینت پی وی پی اوپن سورس ایرانی برای ماینکرفت! ");
        activity.setState("In Game menu");
        activity.timestamps().setStart(Instant.now());
        core.activityManager().updateActivity(activity);

    }

    public void setServer(ServerData data) {
        this.setState("Playing multiplayer (" + data.serverIP + ")");
    }

    public void setScreen(GuiScreen screen) {
        this.setState(screen.getClass().getName().toLowerCase().replace("Gui", ""));
    }

    public void setState(String state) {
        activity.setState(state);
        core.activityManager().updateActivity(activity);
    }

    @Override
    public void run() {
        while (true) {
            core.runCallbacks();
        }
    }
}
