package ir.albino.client.features.ui.clickgui;

import ir.albino.client.AlbinoClient;
import ir.albino.client.features.modules.Module;
import ir.albino.client.utils.render.RenderUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;
import java.io.IOException;

public class GuiDragging extends GuiScreen {

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        final ScaledResolution sr = new ScaledResolution(mc);

        for(Module m : AlbinoClient.instance.moduleManager.getEnabledModules()) {
            RenderUtils.rect(
                    m.getX() - 4,
                    m.getY() - 4,
                    (m.getX() + 3) + m.getWidth(),
                    (m.getY() + 3) + m.getHeight(),
                    new Color(255,255,255,100).getRGB());
        }
        GlStateManager.pushMatrix();
        GlStateManager.disableBlend();
        fontManager().getComfortaa().drawStringWithShadow("Dragging mode is now Active! Drag the mods Anywhere you want!", sr.getScaledWidth() / 3, sr.getScaledHeight() / 2, -1);
        GlStateManager.enableBlend();
        GlStateManager.popMatrix();
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        for(Module m : AlbinoClient.instance.moduleManager.getEnabledModules()) {
            if(!m.isDraggable() && isClicked(m.getX(), m.getY(), mouseX, mouseY, m.getWidth(), m.getHeight()))
                return;

            if(m.isDragging()) {
                m.setX(mouseX);
                m.setY(mouseY);
            }
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for(Module m : AlbinoClient.instance.moduleManager.getEnabledModules()) {
            if(isClicked(m.getX(), m.getY(), mouseX, mouseY, m.getWidth(), m.getHeight())) {
                m.setDragging(true);
            }
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        for(Module m : AlbinoClient.instance.moduleManager.getEnabledModules()) {
            if(!m.isDraggable() && m.isDragging() && isClicked(m.getX(), m.getY(), mouseX, mouseY, m.getWidth(), m.getHeight()))
                return;
            m.setDragging(false);
        }
    }

    public boolean isClicked(int startX, int startY, int mouseX, int mouseY, int width, int height) {
        return mouseX >= startX && mouseY >= startY && mouseX < startX + width && mouseY < startY + height;
    }
}
