package ir.albino.client.features.modules.settings;

import ir.albino.client.features.modules.Module;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
}
