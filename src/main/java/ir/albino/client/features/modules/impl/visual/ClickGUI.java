package ir.albino.client.features.modules.impl.visual;

import ir.albino.client.event.Listener;
import ir.albino.client.event.impl.MouseClickEvent;
import ir.albino.client.event.impl.Render2DEvent;
import ir.albino.client.features.modules.Module;
import ir.albino.client.features.modules.ModuleInfo;
import ir.albino.client.features.ui.clickgui.ClickGuiScreen;

import java.util.ArrayList;
import java.util.List;


@ModuleInfo(module = "ClickGUI", description = "Allows you to Control The mods", version = "1.0", category = ModuleInfo.Category.VISUAL, draggable = false)
public class ClickGUI extends Module {
    @Override
    public void onEnable() {
        mc.displayGuiScreen(new ClickGuiScreen());
        this.toggle();
    }
}
