package net.minecraft.client.gui;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import ir.albino.client.account.AltManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiAltManager extends GuiScreen implements GuiYesNoCallback {
    private static final Logger logger = LogManager.getLogger();
    public AltSelectionList altSelector;
    private String hoveringText;
    @Override
    public void initGui() {
        this.altSelector = new AltSelectionList(this.mc, this.width, this.height, 32, this.height - 64, 36);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.hoveringText = null;
        this.drawDefaultBackground();
        this.altSelector.drawScreen(mouseX, mouseY, partialTicks);
        this.drawCenteredString(this.fontRenderer, I18n.format("altmanager.title"), this.width / 2, 20, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);

        if (this.hoveringText != null) {
            this.drawHoveringText(Lists.newArrayList(Splitter.on("\n").split(this.hoveringText)), mouseX, mouseY);
        }
    }


}
