package ir.albino.client.features.modules.impl.visual;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.albino.client.event.Listener;
import ir.albino.client.event.impl.MouseClickEvent;
import ir.albino.client.event.impl.Render2DEvent;
import ir.albino.client.features.modules.Module;
import ir.albino.client.features.modules.ModuleInfo;
import ir.albino.client.features.modules.settings.ModuleSetting;
import ir.albino.client.features.modules.settings.Setting;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@ModuleInfo(module = "Cps", description = "Shows your Clicks Per Second on the Screen ", version = "1.0", category = ModuleInfo.Category.VISUAL, draggable = true)
public class CpsModule extends Module {
    @Getter
    @Setter
    private int interval = 1000;
    @Getter
    @Setter
    private int delay = 125;

    @Setting
    @JsonIgnore
    public ModuleSetting<Integer> intervalSettings = new ModuleSetting<>("Interval", this::getInterval, this::setInterval);
    @Setting
    @JsonIgnore
    public ModuleSetting<Integer> delaySettings = new ModuleSetting<>("Count Delay", this::getDelay, this::setInterval);
    @JsonIgnore
    private long now = System.currentTimeMillis();

    private final List<Long> cpsList = new ArrayList<>();

    @Listener
    public void onRender2D(Render2DEvent e) {
        long now = System.currentTimeMillis();
        while (!cpsList.isEmpty() && now - cpsList.get(0) >= interval) {
            cpsList.remove(0);
        }
        final String text = String.format("CPS: %s", cpsList.size());

        this.width = mc.fontRendererObj.getStringWidth(text);
        this.height = mc.fontRendererObj.FONT_HEIGHT + 1;

        mc.fontRendererObj.drawStringWithShadow(text, x, y, theme.color);
    }

    @Listener
    public void onMouseClicked(MouseClickEvent event) {
        if (event.key == 0) {
            if (!cpsList.isEmpty() && !(System.currentTimeMillis() - cpsList.get(cpsList.size() - 1) >= delay)) return;
            cpsList.add(System.currentTimeMillis());
        }
    }

}
