package ir.albino.client.features.modules;

import ir.albino.client.AlbinoClient;
import ir.albino.client.features.modules.custom.ModuleTheme;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.minecraft.client.Minecraft;

import java.util.List;
import java.util.stream.Collectors;


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
        System.out.println(this + " " + y);
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

    public void setY(int y) {
        System.out.println("Y has changed " + this.y + "to " + y);
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("%s-%s", getName(), getVersion());
    }
}

