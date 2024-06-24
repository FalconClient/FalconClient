package ir.albino.client.features.modules.settings;

import ir.albino.client.features.ui.clickgui.ClickGuiScreen;
import ir.albino.client.utils.render.RenderUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;

import java.awt.*;
import java.util.function.Consumer;


@Getter
@RequiredArgsConstructor
public class ModuleSetting<V> {
    private final String name;
    private final ModuleFunction<V> getter;
    private final Consumer<V> setter;
    private GuiTextField f;

    public V get() {
        return getter.get();
    }

    public void update(V newVal) {
        this.setter.accept(newVal);
    }

    @FunctionalInterface
    public interface ModuleFunction<V> {
        V get();
    }

    public void render(ClickGuiScreen screen, int x, int y, int width, int height) {
        if (get() instanceof String) {
            this.renderString(screen, x, y, width, height);
        } else if (get() instanceof Boolean) {
            this.renderBoolean(screen, x, y, width, height);
        }
        if (get() instanceof Integer) {
            this.renderInteger(screen, x, y, width, height);
        }
    }

    public void renderBoolean(ClickGuiScreen sc, int x, int y, int width, int height) {
        double x1 = this.calculateX(sc.mc, x);
        RenderUtils.rect(x1, y, x1 + width, y + height, Color.LIGHT_GRAY.getRGB());
        sc.mc.fontRendererObj.drawString(name + ":", x, y, Color.WHITE.getRGB());
    }

    public void renderInteger(ClickGuiScreen sc, int x, int y, int width, int height) {
        double x1 = this.calculateX(sc.mc, x);
        RenderUtils.rect(x1, y, x1 + width, y + height, Color.LIGHT_GRAY.getRGB());
        sc.mc.fontRendererObj.drawString(name + ":", x, y, Color.WHITE.getRGB());
    }

    public void renderString(ClickGuiScreen sc, int x, int y, int width, int height) {
        int x1 = this.calculateX(sc.mc, x);
        if (f == null) {
            f = new GuiTextField(sc.fields.size(), sc.mc.fontRendererObj, x1, y, width, height);
            sc.addTextField(f);
        }
        sc.mc.fontRendererObj.drawString(name + ":", x, y, Color.WHITE.getRGB());
        f.drawTextBox();
    }

    private int calculateX(Minecraft mc, int x) {
        return mc.fontRendererObj.getStringWidth(name + ":") + x + 5;
    }
}
