package ir.albino.client.features.modules.impl.visual;

import ir.albino.client.event.Listener;
import ir.albino.client.event.impl.Render2DEvent;
import ir.albino.client.features.modules.Module;
import ir.albino.client.features.modules.ModuleInfo;
import net.minecraft.client.Minecraft;


@ModuleInfo(module = "Fps", version = "1.0", description = "Shows Your FPS on the Screen", category = ModuleInfo.Category.VISUAL, draggable = true)
public class FpsModule extends Module {
    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Listener
    public void onRender2D(Render2DEvent event) {
        final String text = String.format("FPS: %s", Minecraft.debugFPS);

        this.width = mc.fontRendererObj.getStringWidth(text);
        this.height = mc.fontRendererObj.FONT_HEIGHT + 1;

        mc.fontRendererObj.drawStringWithShadow(text, this.x, this.y, theme.color);
    }


}
