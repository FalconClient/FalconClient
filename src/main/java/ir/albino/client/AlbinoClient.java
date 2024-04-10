package ir.albino.client;

import com.fasterxml.jackson.databind.json.JsonMapper;
import ir.albino.client.event.EventManager;
import ir.albino.client.features.account.AltManager;
import ir.albino.client.features.modules.Module;
import ir.albino.client.features.modules.ModuleManager;
import ir.albino.client.utils.Common;
import ir.albino.client.utils.render.font.FontManager;
import lombok.Getter;
import lombok.SneakyThrows;
import net.minecraft.client.audio.SoundList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AlbinoClient {
    @Getter
    public String NAME = "Albino";
    @Getter
    public String VERSION = "1.0";
    @Getter
    private final Logger logger = LogManager.getLogger(AlbinoClient.class);

    public static AlbinoClient instance = new AlbinoClient();
    public FontManager fontRenderer;
    public AltManager altManager;
    public SoundList soundList;
    public EventManager eventManager;
    public ModuleManager moduleManager;
    public ConcurrentLinkedQueue<Module> modules = new ConcurrentLinkedQueue<>();
    // TODO: do more things by this
    public boolean debug = false;


    public void start() {
        this.detectAccounts();
        this.eventManager = new EventManager();
        this.fontRenderer = new FontManager();
        this.moduleManager = new ModuleManager();
        moduleManager.initModules();
        Display.setTitle(this.NAME);
        this.soundList = new SoundList();


    }

    public void shutDown() {

    }


    public void detectAccounts() {
        File users = new File(Common.getGamePath(), "users.json");
        AltManager manager = new AltManager();
        if (users.exists()) {
            try {
                altManager = new JsonMapper().readValue(users, AltManager.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
