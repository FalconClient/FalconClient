package ir.albino.client.features.ui.altmanager;

import ir.albino.client.AlbinoClient;
import ir.albino.client.features.account.AltManager;
import net.minecraft.client.gui.GuiListExtended;

import java.util.ArrayList;
import java.util.List;

public class AltList extends GuiListExtended {
    public AltManager manager;
    public List<AltEntry> alts = new ArrayList<>();
    public AltEntry selectedAlt = null;

    public AltList(AltManagerMenu menu, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
        super(menu.mc, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
        this.manager = AlbinoClient.instance.altManager;
    }

    public void loadAlts() {
        alts.clear();
        manager.sessions.values().forEach(s -> alts.add(new AltEntry(s.getUsername(), s.getPlayerID(), this)));
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
    public int getAmountScrolled() {
        return super.getAmountScrolled();
    }

    public void removeEntry(AltEntry entry) {
        manager.sessions.remove(entry.username);
        loadAlts();
    }
}
