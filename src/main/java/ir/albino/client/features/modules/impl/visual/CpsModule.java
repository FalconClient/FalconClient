package ir.albino.client.features.modules.impl.visual;

import ir.albino.client.event.Listener;
import ir.albino.client.event.impl.MouseClickEvent;
import ir.albino.client.event.impl.Render2DEvent;
import ir.albino.client.features.modules.Module;
import ir.albino.client.features.modules.ModuleInfo;

import java.util.ArrayList;
import java.util.List;


@ModuleInfo(module = "cps", description = "Shows your Clicks Per Second on the Screen ", version = "1.0", category = ModuleInfo.Category.VISUAL, draggable = true)
public class CpsModule extends Module {
    private final int interval = 1000;
    private final int delay = 125;
    public long now = System.currentTimeMillis();
    private final List<Long> cpsList = new ArrayList<>();

    @Listener
    public void onRender2D(Render2DEvent e) {
        long now = System.currentTimeMillis();
        while (!cpsList.isEmpty() && now - cpsList.get(0) >= interval) {
            cpsList.remove(0);
        }
        mc.fontRendererObj.drawStringWithShadow(String.format("CPS: %s", cpsList.size()), x, y, theme.color);
    }

    @Listener
    public void onMouseClicked(MouseClickEvent event) {
        if (event.key == 0) {
            if (!cpsList.isEmpty() && !(System.currentTimeMillis() - cpsList.get(cpsList.size() - 1) >= delay)) return;
            cpsList.add(System.currentTimeMillis());
        }
    }

}
