package ir.albino.client.features.modules.settings;

import ir.albino.client.features.modules.Module;
import lombok.Getter;

import java.util.function.Consumer;


public class ModuleSetting<V> {
    @Getter
    private final ModuleFunction<V> getter;
    private final String name;
    @Getter
    private final Consumer<V> setter;

    public V get() {
        return getter.get();
    }

    public void update(V newVal) {
        this.setter.accept(newVal);
    }

    public ModuleSetting(String name, ModuleFunction<V> getter, Consumer<V> setter) {
        this.getter = getter;
        this.setter = setter;
        this.name = name;
    }

    @FunctionalInterface
    public interface ModuleFunction<V> {
        V get();
    }
}
