package ir.albino.client.features.ui.html.modules;

import ir.albino.client.features.ui.html.serialize.HTMLParser;
import ir.albino.client.features.ui.html.serialize.HTMLSerializable;
import net.minecraft.client.gui.GuiScreen;

import java.io.File;
import java.io.IOException;

public class HTMLScreen extends GuiScreen implements HTMLSerializable {
    public String background = "";
    protected HTMLParser parser = new HTMLParser();

    public HTMLScreen() {
        this.parse();
    }

    public void parse() {
        File file = getFile();
        if (file.exists()) {
            try {
                parser.parse(file, this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
