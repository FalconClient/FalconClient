package ir.albino.client.features.modules.impl.visual;

import ir.albino.client.event.Listener;
import ir.albino.client.event.impl.Render2DEvent;
import ir.albino.client.features.modules.Module;
import ir.albino.client.features.modules.ModuleInfo;
import lombok.var;
import net.minecraft.client.gui.Gui;



@ModuleInfo(module = "Armors", description = "Shows your armor hp", version = "1.0", draggable = true, category = ModuleInfo.Category.VISUAL)
public class Armors extends Module {
    @Listener
    public void onRender2D(Render2DEvent e) {
//        int k = e.resolution.getScaledWidth() / 2 - 90 + 10 * 20 + 2;
//        if (mc.thePlayer.inventory.armorItemInSlot(0) != null) {
//            var item = mc.thePlayer.inventory.armorItemInSlot(0);
//            var res = item.getItem();
//
//            Gui.drawSingleTexture(k, e.resolution.getScaledHeight(), 10, 10);
//
//        }
    }
}
