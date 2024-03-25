package ir.albino.client.features.modules.impl.visual;

import ir.albino.client.event.Listener;
import ir.albino.client.event.impl.Render2DEvent;
import ir.albino.client.features.modules.Module;
import ir.albino.client.features.modules.ModuleInfo;

@ModuleInfo(Module = "Fps", Version = "1.0", Description = "Shows Your FPS on the Screen", Category = ModuleInfo.Category.VISUAL, Draggable = true)
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
        mc.fontRendererObj.drawStringWithShadow("FPS: kos",0,0,0x988888);
    }
}
