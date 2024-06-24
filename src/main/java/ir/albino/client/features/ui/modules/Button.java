package ir.albino.client.features.ui.modules;

import lombok.AllArgsConstructor;
import lombok.Builder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

import java.util.function.Consumer;


@Builder()
@AllArgsConstructor
public class Button implements AlbinoUI {
    public float width;
    public float height;
    public final Style style;
    private int y;
    private int x;
    @Builder.Default
    public boolean enabled = true;
    @Builder.Default
    public boolean visible = true;

    @Override
    public Style getStyle() {
        return style;
    }

    @Override
    public boolean checkPress(int mouseX, int mouseY, Minecraft mc) {
        return this.enabled && this.visible && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
    }

    @Override
    public void onMouseHover(UIEvent event) {

    }

    private Consumer<UIEvent> onClick;

    public final static String[] DEFAULT_TEXTURES = new String[]{"button/default.png",};

    public Button() {
        style = Style.DEFAULT;
    }

    public void onClick(int x, int y, ScaledResolution sc) {
        onClick.accept(new UIEvent(x, y, sc, this));
    }

    public void render2D(Minecraft mc) {
        if (!visible) return;
        switch (style) {
            case DEFAULT:
                this.renderDefault(mc);
                break;
        }
    }

    private void renderDefault(Minecraft mc) {
        mc.getTextureManager().bindAlbinoTexture(DEFAULT_TEXTURES[0]);
        Gui.drawSingleTexture(x, y, width, height);

    }


}
