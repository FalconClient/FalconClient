package ir.albino.client.features.modules.impl.visual;

import ir.albino.client.event.Listener;
import ir.albino.client.event.impl.Render2DEvent;
import ir.albino.client.features.modules.Module;
import ir.albino.client.features.modules.ModuleInfo;
import lombok.var;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.Collection;

@ModuleInfo(module = "Effects", description = "Shows your current effects", version = "1.0", draggable = true, category = ModuleInfo.Category.VISUAL)
public class Effects extends Module {
    @Listener
    public void onRender2D(Render2DEvent e) {

        int distance = 0;
        for (PotionEffect potionEffect : mc.thePlayer.getActivePotionEffects()) {
            distance += 32;
            renderEffect(e.screen, potionEffect, distance);
        }
        mc.fontRendererObj.drawStringWithShadow("Effects", x, y, theme.color);
    }

    private void renderEffect(Gui screen, PotionEffect e, int y) {
        var res = new ScaledResolution(mc).getScaledWidth() - 64;
        var p = Potion.potionTypes[e.getPotionID()];
        int i = p.getStatusIconIndex();
        this.mc.getTextureManager().bindTexture(InventoryEffectRenderer.inventoryBackground);
        int i1 = p.getStatusIconIndex();
        if (p.hasStatusIcon())
            screen.drawTexturedModalRect(res, y, i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);

        mc.fontRendererObj.drawString(String.valueOf(e.getDuration()), res, y + 16, Color.white.getRGB());
    }
}