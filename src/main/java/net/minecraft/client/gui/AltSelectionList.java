package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import ir.albino.client.account.AltManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AltSelectionList extends GuiListExtended {
    private final Minecraft mc;
    private final AltManager altManager;

    protected final List<AltEntry> alts = Lists.newArrayList();

    public AltSelectionList(Minecraft mc, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
        super(mc, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
        this.mc = mc;
        this.altManager = mc.client.altManager;
        this.loadAlts();
    }

    public void loadAlts() {
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

}
