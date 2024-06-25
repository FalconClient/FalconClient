package ir.albino.client.utils.render;

import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

@UtilityClass
public class RenderUtils {

    public final Minecraft mc = Minecraft.getMinecraft();

    public void drawHead(final ResourceLocation skin, final int width, final int height) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(skin);
        Gui.drawScaledCustomSizeModalRect(width, height, 8.0F, 8.0F, 8, 8, 37, 37, 64.0F, 64.0F);
    }

    public void rect(final double x1, final double y1, final double x2, final double y2, final int fill) {
        GlStateManager.color(0, 0, 0);
        GL11.glColor4f(0, 0, 0, 0);

        float f = (fill >> 24 & 0xFF) / 255.0F;
        float f1 = (fill >> 16 & 0xFF) / 255.0F;
        float f2 = (fill >> 8 & 0xFF) / 255.0F;
        float f3 = (fill & 0xFF) / 255.0F;
        glEnable(GL_BLEND);
        GL11.glDisable(GL_TEXTURE_2D);
        GL11.glBlendFunc(770, 771);
        glEnable(GL_LINE_SMOOTH);

        GL11.glPushMatrix();
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(7);
        GL11.glVertex2d(x2, y1);
        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x1, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();

        glEnable(GL_TEXTURE_2D);
        GL11.glDisable(GL_BLEND);
        GL11.glDisable(GL_LINE_SMOOTH);
    }


}
