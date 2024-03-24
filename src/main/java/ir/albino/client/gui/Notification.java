package ir.albino.client.gui;

import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.XRandR;

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
