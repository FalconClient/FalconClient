package ir.albino.client.features.ui;

import ir.albino.client.features.account.AltManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

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
        this.drawDefaultBackground();
        this.altList.drawScreen(mouseX, mouseY, partialTicks);
        super.drawScreen(mouseX,mouseY,partialTicks);
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.altList.mouseClicked(mouseX, mouseY, mouseButton);
    }

}
