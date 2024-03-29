package ir.albino.client.features.modules.impl.visual;

import ir.albino.client.event.Listener;
import ir.albino.client.event.impl.Render2DEvent;
import ir.albino.client.features.modules.Module;
import ir.albino.client.features.modules.ModuleInfo;

@ModuleInfo(module = "Cps", description = "Shows your Clicks Per Second on the Screen ", version = "1.0", category = ModuleInfo.Category.VISUAL, draggable = true)
public class CpsModule extends Module {
    private int interval = 1000;
    private int currentTime = 0;
    private int cps = 0;

    @Listener
    public void onRender2D(Render2DEvent e) {
        currentTime++;
        if (currentTime == interval) {
            cps = 0;
            currentTime = 0;
        }
    }

}
