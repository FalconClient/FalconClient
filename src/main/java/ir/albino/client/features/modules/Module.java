package ir.albino.client.features.modules;

import ir.albino.client.AlbinoClient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.minecraft.client.Minecraft;

import java.util.List;
import java.util.stream.Collectors;

import static ir.albino.client.features.modules.ModuleTheme.distance;

@Getter
@Setter
@RequiredArgsConstructor
public class Module {
    protected int x;
    protected int y;
    private String name;
    private String description;
    private String version;
    private ModuleInfo.Category category;
    private boolean draggable;
    private boolean enabled;
    public final Minecraft mc = Minecraft.getMinecraft();

    public void onEnable() {
        AlbinoClient client = AlbinoClient.instance;
        List<Module> enabledModules = client.modules.stream().filter(Module::isEnabled).collect(Collectors.toList());
        int newY = 0;
        int i = enabledModules.indexOf(this);
        if (i > 0) {
            client.modules.remove(this);
            client.modules.add(this);
            while (i < enabledModules.size() - 1) {
                enabledModules.get(i).y -= distance;
                i++;
            }
            newY = enabledModules.get(enabledModules.size() - 1).y + distance;
        }
        this.setY(newY);
    }


    public void onDisable() {
        AlbinoClient client = AlbinoClient.instance;
        List<Module> enabledModules = client.modules.stream().filter(Module::isEnabled).collect(Collectors.toList());
        int i = enabledModules.indexOf(this);
        if (!client.modules.isEmpty()) {
            while (i > enabledModules.size() - 1) {
                enabledModules.get(i).y -= distance;
            }
        }

        this.setY(0);
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

}
