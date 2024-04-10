package ir.albino.client.utils.render.font;

import ir.albino.client.AlbinoClient;
import lombok.Getter;
import lombok.SneakyThrows;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;

@Getter
public class FontManager {

    private final AlbinoFontRenderer productSans;
    private final AlbinoFontRenderer productSans23;
    private final AlbinoFontRenderer productSansTitle;
    private final AlbinoFontRenderer comfortaa;

    public FontManager() {
        productSans = new AlbinoFontRenderer(getFontFromTTF("product_sans", 20, Font.PLAIN), true, true);
        productSans23 = new AlbinoFontRenderer(getFontFromTTF("product_sans", 23, Font.PLAIN), true, true);
        productSansTitle = new AlbinoFontRenderer(getFontFromTTF("product_sans", 34, Font.PLAIN), true, true);
        comfortaa = new AlbinoFontRenderer(getFontFromTTF("comfortaa", 19, Font.PLAIN), true, true);
    }

    public Font getFontFromTTF(String fontName, float fontSize, int fontType) {
        Font output = null;
        ResourceLocation fontLocation = new ResourceLocation("Albino/fonts/" + fontName + ".ttf");
        try {
            output = Font.createFont(fontType, Minecraft.getMinecraft().getResourceManager().getResource(fontLocation).getInputStream());
        } catch (FontFormatException | IOException e) {
            new CrashReport("Failed to load font", e);
        }
        output = output.deriveFont(fontSize);
        return output;
    }
}
