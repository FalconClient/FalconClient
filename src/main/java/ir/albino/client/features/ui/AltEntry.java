package ir.albino.client.features.ui;

import ir.albino.client.AlbinoClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.util.ResourceLocation;

public class AltEntry implements GuiListExtended.IGuiListEntry {
    public final String uuid;
    public final String username;
    private ResourceLocation resource = new ResourceLocation("textures/gui/itembox.png");

    public AltEntry(String username, String uuid) {
        this.username = username;
        this.uuid = uuid;
    }

    @Override
    public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {

    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(resource);
        Gui.drawSingleTexture(x, y, listWidth, slotHeight);
        AlbinoClient.instance.fontRenderer.drawString(username, x, y,0);
    }

    @Override
    public boolean mousePressed(int slotIndex, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
        return false;
    }

    @Override
    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {

    }
}
