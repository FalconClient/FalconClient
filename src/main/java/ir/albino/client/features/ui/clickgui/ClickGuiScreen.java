package ir.albino.client.features.ui.clickgui;

import ir.albino.client.AlbinoClient;
import ir.albino.client.features.modules.Module;
import ir.albino.client.utils.render.RenderUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;
import java.io.IOException;

public class ClickGuiScreen extends GuiScreen {


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        final ScaledResolution sr = new ScaledResolution(mc);
        final String title = String.format("%s %s | ClickGUI", AlbinoClient.instance.NAME, AlbinoClient.instance.VERSION);

        //Background
        RenderUtils.rect(30, 30, sr.getScaledWidth() - 30, sr.getScaledHeight() - 30, new Color(0, 0, 0, 120).getRGB());

        //Top-Rect
        RenderUtils.rect(30, 30, sr.getScaledWidth() - 30, 30 + fontManager().getComfortaa().getHeight() + 6, new Color(0, 0, 0, 140).getRGB());

        //Modules-Background
        RenderUtils.rect(30, 30, mc.fontRendererObj.getStringWidth(title) + 40, sr.getScaledHeight() - 30, new Color(0, 0, 0, 140).getRGB());


        //Title Text
        GlStateManager.pushMatrix();
        GlStateManager.disableBlend();

        fontManager().getComfortaa().drawStringWithShadow(
                title,
                35, 35, -1
        );

        GlStateManager.enableBlend();
        GlStateManager.popMatrix();

        //Rendering the Modules

        int y = 0;
        for (Module m : AlbinoClient.instance.modules) {
            fontManager().getComfortaa().drawStringWithShadow(m.getName(), 36, 55 + y, -1);
            y += 15;
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        int y = 0;
        for (Module m : AlbinoClient.instance.modules) {
            if (isClicked(36, 55 + y, mouseX, mouseY, mc.fontRendererObj.getStringWidth(m.getName()) + 3, 6)) {
                m.toggle();
            }
            y += 15;
        }

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public boolean isClicked(int startX, int startY, int mouseX, int mouseY, int width, int height) {
        return mouseX >= startX && mouseY >= startY && mouseX < startX + width && mouseY < startY + height;
    }
}
