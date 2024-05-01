package ir.albino.client.features.modules.settings;

import ir.albino.client.features.modules.Module;
import ir.albino.client.utils.render.RenderUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.function.Consumer;


@Getter
@AllArgsConstructor
public class ModuleSetting<V> {
    private final String name;
    private final ModuleFunction<V> getter;
    private final Consumer<V> setter;

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

    public void render(Minecraft mc, int x, int y, int width, int height) {
        double x1 = mc.fontRendererObj.getStringWidth(name + ":") + x + 5;
        RenderUtils.rect(x1, y, x1 + width, y + height, Color.LIGHT_GRAY.getRGB());
        mc.fontRendererObj.drawString(name + ":", x, y, Color.WHITE.getRGB());
    }
}
