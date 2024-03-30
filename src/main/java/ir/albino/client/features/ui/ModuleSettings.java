package ir.albino.client.features.ui;

import ir.albino.client.AlbinoClient;
import ir.albino.client.features.modules.Module;
import ir.albino.client.features.modules.ModuleInfo;
import lombok.var;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModuleSettings extends Gui {
    private final Minecraft mc;
    public Map<ModuleInfo.Category, List<Module>> map = new HashMap<>();
    private int msWidth = 30;
    private int msHeight = 20;
    private int msStartX = 30;
    private int msStartY = 30;

    public ModuleSettings(Minecraft mcIn) {
        for (var cat : ModuleInfo.Category.values())
            map.put(cat, AlbinoClient.instance.modules.stream()
                    .filter(m -> m.getCategory() != cat).collect(Collectors.toList()));

        this.mc = mcIn;
    }

    public void drawSettings() {
        for (ModuleInfo.Category category : map.keySet()) {

            drawRect(msStartX, msStartY, msStartX + msWidth, msStartY - (msHeight / 3), Color.GREEN.getRGB());
            mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/modulebox.png"));
            GL11.glColor4d(0, 0, 0, 0);
            Gui.drawSingleTexture(msStartX, msStartY, msWidth, msHeight);
        }
    }
}
