package net.minecraft.client.gui.altmanager;

import com.google.common.collect.Lists;
import ir.albino.client.account.AltManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AltEntry implements GuiListExtended.IGuiListEntry {
    private final Minecraft mc;
    private static final Logger logger = LogManager.getLogger();

    private final AltManager altManager;
    private final Session session;

    public AltEntry(Minecraft mc, Session session) {
        this.mc = mc;
        this.altManager = mc.client.altManager;
        this.session = session;
    }

    @Override
    public boolean mousePressed(int slotIndex, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
        altManager.makeCurrentSession(session);
        return false;
    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.drawRect(x - 1, y - 1, x + listWidth - 9, y + slotHeight + 1, -8978432);

        this.mc.fontRendererObj.drawString(session.getUsername(), x + 32 + 3, y + 1, 16777215);
        // TODO: rendering alt skin
    }

    @Override
    public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {

    }


    @Override
    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {

    }
}
