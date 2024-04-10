package ir.albino.client.features.ui.html.modules;

import ir.albino.client.features.ui.html.serialize.HTMLSerializable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

public abstract class HTMLListExtended extends GuiListExtended {

    public HTMLListExtended(Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
        super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
    }

    public static abstract class HTMLEntry implements IGuiListEntry, HTMLSerializable {
        public String xStr = "x + listWidth / 1.9";
        public String yStr = "y + slotHeight / 2.3";

    }
}
