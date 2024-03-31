package ir.albino.client.features.ui;

import ir.albino.client.AlbinoClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import java.awt.*;

public class AltEntry implements GuiListExtended.IGuiListEntry {
    public final String uuid;
    public final String username;
    private static Minecraft mc = Minecraft.getMinecraft();
    private final AltList list;
    private ResourceLocation resource = new ResourceLocation("textures/gui/itembox.png");

    public AltEntry(String username, String uuid, AltList altList) {
        this.username = username;
        this.uuid = uuid;
        this.list = altList;
    }

    @Override
    public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {

    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
        mc.getTextureManager().bindTexture(resource);
        Gui.drawSingleTexture(x, y, listWidth, slotHeight);
        AlbinoClient.instance.fontRenderer.getComfortaa().drawCenteredString(username, x + (float) listWidth / 1.9, y + slotHeight / 2.3, Color.white.getRGB());
    }

    public static void drawHead(ResourceLocation skin, int x, int y, int width, int height) {
        try {
            mc.getTextureManager().bindTexture(skin);
            GlStateManager.enableBlend();
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glColor4f(1, 1, 1, 1);
            Gui.drawScaledCustomSizeModalRect(x, y, 8F, 8F, 8, 8, width, height, 64F, 64F);
            GL11.glDisable(GL11.GL_BLEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean mousePressed(int slotIndex, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
        for (AltEntry alt : list.alts) {
            if (alt != this) alt.select(false);
        }
        this.select(true);
        return false;
    }

    public void select(boolean b) {
        if (b) {
            resource = new ResourceLocation("textures/gui/itembox-selected.png");
        } else resource = new ResourceLocation("textures/gui/itembox.png");
    }

    @Override
    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {

    }
}
