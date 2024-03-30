package ir.albino.client.features.ui.chat;

import ir.albino.client.AlbinoClient;
import net.minecraft.client.gui.GuiScreen;

public class ChatMenu extends GuiScreen {
    private final GuiScreen parent;
    private AlbinoClient client;
    public ChatMenu(GuiScreen parent) {
        this.parent = parent;
        this.client = AlbinoClient.instance;
    }

    @Override
    public void initGui() {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
