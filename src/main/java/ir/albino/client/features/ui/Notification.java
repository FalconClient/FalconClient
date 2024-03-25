package ir.albino.client.features.ui;

import net.minecraft.client.gui.GuiScreen;

public class Notification extends GuiScreen {
    public Notification(String msg) {
        width = 100;
        height = 20;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
