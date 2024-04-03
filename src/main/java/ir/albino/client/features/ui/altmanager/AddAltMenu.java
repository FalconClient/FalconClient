package ir.albino.client.features.ui.altmanager;

import ir.albino.client.AlbinoClient;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class AddAltMenu extends GuiScreen {
    private final AltManagerMenu parent;
    private GuiTextField textField;

    public AddAltMenu(AltManagerMenu parent) {
        this.parent = parent;
    }

    @Override
    public void initGui() {
        this.createButtons();
        int height = 30;
        int width = 200;
        this.textField = new GuiTextField(2, mc.fontRendererObj, (this.width - width) / 2, (this.height + height) / 2, width, height);
        textField.setFocused(true);
    }

    private void createButtons() {
        buttonList.addAll(Arrays.asList(
                new GuiButton(0, 20, height - 30, 100, 20, I18n.format("gui.done")),
                new GuiButton(1, width - 220, height - 30, 100, 20, I18n.format("gui.cancel"))
        ));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        textField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
                AlbinoClient.instance.altManager.addOfflineSession(textField.getText().replaceAll(" ", "")
                        , UUID.randomUUID());
                mc.displayGuiScreen(parent);
                parent.reloadAlts();
                break;
            case 1:
                mc.displayGuiScreen(parent);
                break;
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        textField.textboxKeyTyped(typedChar, keyCode);
    }
}
