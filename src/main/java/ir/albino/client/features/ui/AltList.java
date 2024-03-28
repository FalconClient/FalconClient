package ir.albino.client.features.ui;

import ir.albino.client.AlbinoClient;
import ir.albino.client.features.account.AltManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AltList extends GuiListExtended {
    public AltManager manager;
    public List<AltEntry> alts = new ArrayList<>();

    public AltList(AltManagerMenu menu, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
        super(menu.mc, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
        this.manager = AlbinoClient.instance.altManager;
        loadAlts();
    }

    public void loadAlts() {
        alts.clear();
        manager.sessions.values().forEach(s -> alts.add(new AltEntry(s.getUsername(), s.getPlayerID())));
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
