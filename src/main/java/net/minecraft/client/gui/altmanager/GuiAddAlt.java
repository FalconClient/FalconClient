package net.minecraft.client.gui.altmanager;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;

import java.io.IOException;
import java.util.UUID;

public class GuiAddAlt extends GuiScreen {
    private final GuiScreen parentScreen;
    private GuiTextField textAltName;
    private GuiButton btnDone;
    private GuiButton btnCancel;



    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();
        this.textAltName = new GuiTextField(1, this.fontRendererObj, this.width / 2 - 100, this.height / 2 , 200, 20);
        textAltName.setMaxStringLength(32);
        createButtons();
        textAltName.setFocused(true);


    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        textAltName.textboxKeyTyped(typedChar, keyCode);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        textAltName.drawTextBox();
    }

    public void createButtons() {
        buttonList.add(btnDone = new GuiButton(0, textAltName.xPosition + 100, textAltName.yPosition - 40, 70, 20, I18n.format("gui.done")));
        buttonList.add(btnCancel = new GuiButton(2, btnDone.xPosition - 100, btnDone.yPosition, 70, 20, I18n.format("gui.cancel")));
    }


    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
                this.parentScreen.confirmClicked(true, 0);
                mc.client.altManager.addOfflineSession(textAltName.getText(), UUID.randomUUID());
                break;
            case 2:
                this.parentScreen.confirmClicked(false, 1);
                break;
        }
    }

    public GuiAddAlt(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

}
