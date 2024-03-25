package ir.albino.client;

import ir.albino.client.account.AltManager;
import ir.albino.client.event.EventManager;
import ir.albino.client.gui.font.AlbinoFontRenderer;
import ir.albino.client.utils.Common;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.File;

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


    public void start() {
        this.createGameDirectory();
        logger.info(String.format("Loading %s %s", NAME, VERSION));
        this.eventManager = new EventManager();
        this.fontRenderer = new AlbinoFontRenderer("vazir", 20, Font.PLAIN, true, false);
        this.altManager = new AltManager();
    }

    public void shutDown() {

    }


    private void createGameDirectory() {
        File file = Common.getGamePath();
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
