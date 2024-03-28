package ir.albino.client.features.ui;

import ir.albino.client.features.account.AltManager;
import net.minecraft.client.gui.GuiScreen;

public class AltManagerMenu extends GuiScreen {
    public final GuiScreen parent;
    private AltList altList;

    public AltManagerMenu(GuiScreen parent) {
        this.parent = parent;
    }

    @Override
    public void initGui() {
        this.altList = new AltList(this,width,height,32,this.height - 64,36);
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.altList.drawScreen(mouseX, mouseY, partialTicks);
    }
}
