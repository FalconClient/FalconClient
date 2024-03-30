package ir.albino.client.features.ui.chat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

public class ChatList extends GuiListExtended {
    public ChatList(ChatMenu menu, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
        super(menu.mc, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
    }

    @Override
    public IGuiListEntry getListEntry(int index) {
        return null;
    }

    @Override
    protected int getSize() {
        return 0;
    }
}
