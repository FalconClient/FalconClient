package ir.albino.client.features.ui.altmanager;

import ir.albino.client.AlbinoClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public final class AltEntry implements GuiListExtended.IGuiListEntry {
    public final String uuid;
    public final String username;
    private static final Minecraft mc = Minecraft.getMinecraft();
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
        GlStateManager.enableBlend();
        mc.getTextureManager().bindTexture(resource);
        Gui.drawSingleTexture(x, y, listWidth, slotHeight);
        GlStateManager.disableBlend();
        AlbinoClient.instance.fontRenderer.getComfortaa().drawCenteredStringWithShadow(username, x + (float) listWidth / 1.9, y + slotHeight / 2.3, Color.white.getRGB());
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
            list.selectedAlt = this;
        } else {
            resource = new ResourceLocation("textures/gui/itembox.png");
            if (list.selectedAlt == this) list.selectedAlt = null;
        }
    }

    @Override
    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {

    }
}
