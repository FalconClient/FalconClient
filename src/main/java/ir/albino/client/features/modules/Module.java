package ir.albino.client.features.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.albino.client.AlbinoClient;
import ir.albino.client.features.modules.configuration.ModuleTheme;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.minecraft.client.Minecraft;

import java.util.List;
import java.util.stream.Collectors;


@Data
public class Module {
    @JsonIgnore
    protected int x;
    @JsonIgnore
    protected int y;
    @JsonIgnore
    private String name;
    @JsonIgnore
    private String description;
    @JsonIgnore
    private String version;
    @JsonIgnore
    private ModuleInfo.Category category;
    @JsonIgnore
    private boolean draggable;
    private boolean enabled;
    @JsonIgnore
    public final Minecraft mc = Minecraft.getMinecraft();
    @JsonIgnore
    public ModuleTheme theme = AlbinoClient.instance.moduleManager.getModuleTheme();

    public void onEnable() {
        AlbinoClient client = AlbinoClient.instance;
        List<Module> enabledModules = client.modules.stream().filter(Module::isEnabled).collect(Collectors.toList());
        int i = enabledModules.indexOf(this);
        int newY = 0;
        if (i > 0) {
            int i1 = client.modules.indexOf(this);
            if (i1 != client.modules.size() - 1) {
                client.modules.remove(this);
                client.modules.add(this);
            }
            while (i > enabledModules.size() - 1) {
                Module m = enabledModules.get(i);
                m.setY(m.getY() - theme.distance);
                i++;
            }
            Module last = enabledModules.get(enabledModules.size() - 1);
            newY = last.y + theme.distance;
        }
        this.setY(newY);
    }


    public void onDisable() {
        AlbinoClient client = AlbinoClient.instance;
        List<Module> enabledModules = client.modules.stream().filter(Module::isEnabled).collect(Collectors.toList());
        int i = enabledModules.indexOf(this);
        if (!client.modules.isEmpty()) {
            while (i > enabledModules.size() - 1) {
                Module m = enabledModules.get(i);
                m.setY(m.getY() - theme.distance);
            }
        }
        setY(0);
    }

    public void onInit() {
    }

    public final void toggle() {
        this.enabled = !enabled;
        AlbinoClient client = AlbinoClient.instance;
        if (enabled) {
            onEnable();
            client.eventManager.register(this);
        } else {
            onDisable();
            client.eventManager.unregister(this);
        }
    }

    @Override
    public String toString() {
        return String.format("%s-%s", getName(), getVersion());
    }
}

