package ir.albino.client.features.ui;

import ir.albino.client.AlbinoClient;
import ir.albino.client.features.ui.altmanager.AltManagerMenu;
import ir.albino.client.features.ui.chat.ChatMenu;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;

public class MainMenu extends GuiScreen {
    @Override
    public void initGui() {
        int j = this.height / 4 + 48;
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, j + 72 + 12, 98, 20, I18n.format("menu.options")));
        this.buttonList.add(new GuiButton(4, this.width / 2 + 2, j + 72 + 12, 98, 20, I18n.format("menu.quit")));
        this.buttonList.add(new GuiButtonLanguage(5, this.width / 2 - 124, j + 72 + 12));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, j, I18n.format("menu.singleplayer")));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, j + 22, I18n.format("menu.multiplayer")));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, j + 44, I18n.format("menu.altmanager")));
//        this.buttonList.add(new GuiButton(6, this.width / 2 - 150, j + 44, I18n.format("menu.chat")));
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 1:
                this.mc.displayGuiScreen(new GuiSelectWorld(this));
                break;
            case 2:
                this.mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case 3:
                this.mc.displayGuiScreen(new AltManagerMenu(this));
                break;
            case 4:
                this.mc.shutdown();
                break;
            case 6:
                this.mc.displayGuiScreen(new ChatMenu(this));
                break;
            case 5:
                this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
                break;
        }
        super.actionPerformed(button);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        final ScaledResolution sr = new ScaledResolution(mc);

        mc.getTextureManager().bindTexture(new ResourceLocation("Albino/images/background.png"));
        Gui.drawModalRectWithCustomSizedTexture(
                -21 + mouseX / 90,
                mouseY * -1 / 90,
                0.0f, 0.0f,
                this.width + 20,
                this.height + 20,
                (float) (this.width + 21),
                (float) (this.height + 20)
        );

        //RenderUtils.rect(5,10,50,100, new Color(0,0,0,155).getRGB());
        GlStateManager.pushMatrix();
        GlStateManager.disableBlend();
        AlbinoClient.instance.fontRenderer.getComfortaa().drawStringWithShadow(String.format(
                        "%s %s By AlbinoTeam",
                        AlbinoClient.instance.NAME,
                        AlbinoClient.instance.VERSION),
                5,
                sr.getScaledHeight() - 15,
                new Color(141, 211, 211).getRGB()
        );
        GlStateManager.enableBlend();
        GlStateManager.popMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
