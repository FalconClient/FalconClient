package net.minecraft.client.gui.altmanager;

import com.google.common.collect.Lists;
import ir.albino.client.account.AltManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.util.Session;

import java.util.List;

public class AltSelectionList extends GuiListExtended {
    private final Minecraft mc;
    private final AltManager altManager;

    private final List<AltEntry> alts = Lists.newArrayList();

    public AltSelectionList(Minecraft mc, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
        super(mc, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
        this.mc = mc;
        this.altManager = mc.client.altManager;
        this.loadAlts();
    }


    public void loadAlts() {
        alts.clear();
        for (Session session : altManager.sessions.values())
            alts.add(new AltEntry(mc, session));
    }

    @Override
    public IGuiListEntry getListEntry(int index) {
        return alts.get(index);
    }

    @Override
    protected int getSize() {
        return alts.size();
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseEvent) {
        return super.mouseClicked(mouseX, mouseY, mouseEvent);

    }
}
