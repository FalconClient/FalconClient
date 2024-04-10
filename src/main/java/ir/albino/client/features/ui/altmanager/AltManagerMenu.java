package ir.albino.client.features.ui.altmanager;

import ir.albino.client.features.ui.html.annotations.Event;
import ir.albino.client.features.ui.html.annotations.HTMLIgnore;
import ir.albino.client.features.ui.html.modules.HTMLButton;
import ir.albino.client.features.ui.html.modules.HTMLScreen;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonLanguage;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;

import java.io.IOException;
import java.util.Arrays;

public class AltManagerMenu extends HTMLScreen implements GuiYesNoCallback {
    @HTMLIgnore
    public final GuiScreen parent;
    private AltList altList;
    @HTMLIgnore
    private boolean initialized;
    private GuiButton btnRemove;

    public AltManagerMenu(GuiScreen parent) {
        this.parent = parent;
    }

    @Event(eventType = Event.Type.BEFORE_SERIALIZE)
    public void onPreSerialize() {
        this.altList = new AltList(this, width, height, 32, this.height - 64, 36);
        this.initialized = true;
        this.altList.loadAlts();
    }

    @Override
    public void initGui() {
        if (initialized) {
            this.altList.setDimensions(this.width, this.height, 32, this.height - 64);
        }
        this.altList.registerScrollButtons(9, 10);
        this.createButtons();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.altList.drawScreen(mouseX, mouseY, partialTicks);
        super.drawScreen(mouseX, mouseY, partialTicks);
        btnRemove.enabled = altList.selectedAlt != null;

    }

    public void createButtons() {
        buttonList.addAll(Arrays.asList(new GuiButton(0, this.width - 110, height - 55, 100, 20, I18n.format("altmanager.add")), btnRemove = new GuiButton(1, this.width - 110, height - 30, 100, 20, I18n.format("altmanager.remove")), new GuiButton(2, 10, height - 30, 100, 20, I18n.format("gui.done"))));
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.altList.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
                mc.displayGuiScreen(new AddAltMenu(this));
                break;
            case 1:
                altList.removeEntry(altList.selectedAlt);
                break;
            case 2:
                mc.displayGuiScreen(parent);
                break;

        }
    }

    public void reloadAlts() {
        altList.loadAlts();
    }
}
