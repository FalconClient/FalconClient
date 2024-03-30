package ir.albino.client.features.modules.configuration;

import net.minecraft.client.Minecraft;

public class ModuleTheme {
    @ModuleField(displayName = "Module Text Color", description = "Defines the color for your modules text")
    public int color = 0x988888;

    @ModuleField(displayName = "Text Distances", description = "Distance between each texts horizontally")
    public int distance = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 6;

}
