package ir.albino.client.features.modules;

import ir.albino.client.AlbinoClient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.minecraft.client.Minecraft;

@Getter
@Setter
@RequiredArgsConstructor
public class Module {

    private String name;
    private String description;
    private String version;
    private ModuleInfo.Category category;
    private boolean draggable;
    private boolean enabled;

    public final Minecraft mc = Minecraft.getMinecraft();

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onInit() {
    }

    public final void toggle() {
        this.enabled = !enabled;

        if (enabled) {
            onEnable();
            AlbinoClient.instance.eventManager.register(this);
        } else {
            onDisable();
            AlbinoClient.instance.eventManager.unregister(this);
        }
    }


}
