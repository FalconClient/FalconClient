package ir.albino.client.features.ui.modules;

import lombok.AllArgsConstructor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public interface AlbinoUI {
    void render2D(Minecraft mc);

    Style getStyle();

    boolean checkPress(int x, int y, Minecraft mc);

    enum Style {
        DEFAULT,

    }

    void onMouseHover(UIEvent event);


    @AllArgsConstructor
    class UIEvent {
        public int x;
        public int y;
        public ScaledResolution sc;
        public AlbinoUI ui;
    }

}
