package ir.albino.client.features.ui;

import dev.slangware.ultralight.HtmlScreen;
import dev.slangware.ultralight.UltraManager;
import dev.slangware.ultralight.ViewController;
import dev.slangware.ultralight.annotations.HTMLRoute;
import ir.albino.client.AlbinoClient;
import ir.albino.client.features.ui.altmanager.AltManagerMenu;
import ir.albino.client.features.ui.chat.ChatMenu;
import ir.albino.client.utils.Common;
import net.janrupf.ujr.api.config.UlViewConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;

public class MainMenu extends HtmlScreen {

    public GuiButton btnOptions;
    public GuiButton btnSinglePlayer;
    public GuiButton btnQuit;
    public GuiButton btnMultiPlayer;
    public GuiButton btnLanguage;
    public GuiButton btnAltManager;
    private final static AlbinoClient client = AlbinoClient.instance;

    public MainMenu() {
        super(UltraManager.getInstance().getViewController(), "mainmenu.html");
    }

    @Override
    public void initGui() {
        btnOptions = new GuiButton(0, width / 2 - 100, height / 4 + 48 + 84, 98, 20, I18n.format("menu.options"));
        btnSinglePlayer = new GuiButton(1, width / 2 - 100, height / 4 + 48, I18n.format("menu.singleplayer"));
        btnQuit = new GuiButton(4, width / 2 + 2, height / 4 + 48 + 84, 98, 20, I18n.format("menu.quit"));
        btnMultiPlayer = new GuiButton(2, width / 2 - 100, height / 4 + 48 + 22, I18n.format("menu.multiplayer"));
        btnLanguage = new GuiButtonLanguage(5, width / 2 - 124, height / 4 + 48 + 72 + 12);
        btnAltManager = new GuiButton(3, width / 2 - 100, height / 4 + 48 + 44, I18n.format("menu.altmanager"));
        this.buttonList.add(btnOptions);
        this.buttonList.add(btnQuit);
        this.buttonList.add(btnSinglePlayer);
        this.buttonList.add(btnLanguage);
        this.buttonList.add(btnMultiPlayer);
        this.buttonList.add(btnAltManager);
//        this.buttonList.add(new GuiButton(6, this.width / 2 - 150, j + 44, I18n.format("menu.chat")));
//        try {
//            save();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
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
