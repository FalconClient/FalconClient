package ir.albino.client;

import com.fasterxml.jackson.databind.json.JsonMapper;
import ir.albino.client.event.EventManager;
import ir.albino.client.features.account.AltManager;
import ir.albino.client.features.modules.Module;
import ir.albino.client.features.modules.ModuleManager;
import ir.albino.client.features.ui.font.AlbinoFontRenderer;
import ir.albino.client.utils.Common;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AlbinoClient {
    @Getter
    public String NAME = "Albino";
    @Getter
    public String VERSION = "1.0";
    @Getter
    private final Logger logger = LogManager.getLogger(AlbinoClient.class);


    public static AlbinoClient instance = new AlbinoClient();
    public AlbinoFontRenderer fontRenderer;
    public AltManager altManager;
    public EventManager eventManager;
    public ModuleManager moduleManager;
    public ArrayList<Module> modules = new ArrayList<>();


    public void start() {
        logger.info(String.format("Loading %s %s", NAME, VERSION));
        this.eventManager = new EventManager();
        this.fontRenderer = new AlbinoFontRenderer("vazir", 20, Font.PLAIN, true, false);
        this.detectAccounts();
        this.moduleManager = new ModuleManager();
        moduleManager.initModules();

        Display.setTitle(this.NAME);
    }

    public void shutDown() {

    }

    @SneakyThrows
    public void detectAccounts() {
        File users = new File(Common.getGamePath(), "users.json");
        AltManager manager = new AltManager();
        if (users.exists()) {
            altManager = new JsonMapper().readValue(users, AltManager.class);
        }
    }

}
