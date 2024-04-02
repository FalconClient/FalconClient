package ir.albino.client.features.modules.settings;

import ir.albino.client.utils.MathUtils;

import java.util.function.Consumer;

public class DraggableSettings extends ModuleSetting<Float> {
    private final float max;

    public DraggableSettings(String name, float max, ModuleFunction<Float> getter, Consumer<Float> setter) {
        super(name, getter, setter);
        this.max = max;

    }

    public void add(Float add) {
        float sum = get() + add;
        this.update(Math.min(sum, max));
    }

    public void remove(Float remove) {
        float sum = get() - remove;
        this.update(Math.max(0, sum));
    }
}
