package net.minecraft.client.gui;

import ir.albino.client.features.ui.html.annotations.ConstructorInjection;
import ir.albino.client.features.ui.html.modules.HTMLButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class GuiButtonLanguage extends HTMLButton {
    public GuiButtonLanguage(int buttonID, int xPos, int yPos) {
        super(buttonID, String.valueOf(xPos), String.valueOf(yPos), "20", "20", "");
    }

    @ConstructorInjection(attrs = {"buttonID", "xPos", "yPos"})
    public GuiButtonLanguage(int buttonID, String xPos, String yPos) {
        super(buttonID, xPos, yPos, "20", "20", "");
    }

    /**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            mc.getTextureManager().bindTexture(GuiButton.buttonTextures);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            boolean flag = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int i = 106;

            if (flag) {
                i += this.height;
            }

            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, i, this.width, this.height);
        }
    }
}
