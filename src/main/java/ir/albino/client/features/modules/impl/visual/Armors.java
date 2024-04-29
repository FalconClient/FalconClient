package ir.albino.client.features.modules.impl.visual;

import ir.albino.client.event.Listener;
import ir.albino.client.event.impl.Render2DEvent;
import ir.albino.client.features.modules.Module;
import ir.albino.client.features.modules.ModuleInfo;
import lombok.var;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;


@ModuleInfo(module = "Armor", description = "Shows your armor hp", version = "1.0", draggable = true, category = ModuleInfo.Category.VISUAL)
public class Armors extends Module {
    @Listener
    public void onRender2D(Render2DEvent e) {
        int distance = 0;
        for (int i = 3; i >= 0; i--) {
            this.renderArmor(i, distance);
            distance += 25;
        }
        mc.fontRendererObj.drawStringWithShadow("Armors", x, y, theme.color);
    }

    private void renderArmor(int slot, int y) {
        if (mc.thePlayer.inventory.armorItemInSlot(slot) != null) {
            var item = mc.thePlayer.inventory.armorItemInSlot(slot);
            var res = new ScaledResolution(mc).getScaledWidth() - 32;
            mc.getRenderItem().renderItemIntoGUI(item, res, y);
            mc.fontRendererObj.drawString(item.getMaxDamage() - item.getItemDamage() + "/" + item.getMaxDamage(), res, y + 16, Color.white.getRGB());
        }
    }
}
