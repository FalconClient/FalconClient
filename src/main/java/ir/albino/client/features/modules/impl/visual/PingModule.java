package ir.albino.client.features.modules.impl.visual;

import ir.albino.client.event.Listener;
import ir.albino.client.event.impl.Render2DEvent;
import ir.albino.client.features.modules.Module;
import ir.albino.client.features.modules.ModuleInfo;


@ModuleInfo(module = "ping", description = "Shows your current ping", draggable = true,
        category = ModuleInfo.Category.VISUAL, version = "1.0")
public class PingModule extends Module {
    @Listener
    public void onRender2D(Render2DEvent e) {
        long ping = 0;
        if (mc.getCurrentServerData() != null) ping = mc.getCurrentServerData().pingToServer;
        mc.fontRendererObj.drawStringWithShadow(String.format("Ping: %s", ping), x, y, theme.color);
    }
}
