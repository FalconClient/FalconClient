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
    private final GuiAltManager parent;

    private final List<AltEntry> alts = Lists.newArrayList();
    private AltEntry selectedEntry;

    public AltSelectionList(Minecraft mc, GuiAltManager parent, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
        super(mc, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
        this.mc = mc;
        this.altManager = mc.client.altManager;
        this.parent = parent;
        this.loadAlts();
    }


    public void loadAlts() {
        alts.clear();
        for (Session session : altManager.sessions.values())
            alts.add(new AltEntry(mc, session, parent));
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

    public void selectAlt(AltEntry entry) {
        this.selectedElement = alts.indexOf(entry);
    }
}
