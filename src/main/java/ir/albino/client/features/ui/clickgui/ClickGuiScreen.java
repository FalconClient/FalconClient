package ir.albino.client.features.ui.clickgui;

import ir.albino.client.AlbinoClient;
import ir.albino.client.features.modules.Module;
import ir.albino.client.features.modules.impl.visual.Armors;
import ir.albino.client.features.modules.impl.visual.ClickGUI;
import ir.albino.client.features.modules.settings.DraggableSettings;
import ir.albino.client.features.modules.settings.ModuleSetting;
import ir.albino.client.utils.BoundingBox;
import ir.albino.client.utils.render.RenderUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

public class ClickGuiScreen extends GuiScreen {
    public Map<Module, BoundingBox> map = new HashMap<>();
    public Module selected;
    public List<GuiTextField> fields = new ArrayList<>();

    /**
     * @param margin based margin
     * @return an array of integers containing 4 ints. {x1,y1,x1,y2}
     */
    private int[] calculateMargin(int margin) {
        ScaledResolution res = new ScaledResolution(mc);
        int marginTop = res.getScaledHeight() - margin;
        int marginRight = res.getScaledWidth() - margin;
        return new int[]{margin, margin, marginRight, marginTop};
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        final ScaledResolution sr = new ScaledResolution(mc);
        final String title = String.format("%s %s | ClickGUI", AlbinoClient.instance.NAME, AlbinoClient.instance.VERSION);
        int[] margins = calculateMargin(30);
        //Background
        RenderUtils.rect(margins[0], margins[1], margins[2], margins[3], new Color(0, 0, 0, 120).getRGB());

        //Top-Rect
        RenderUtils.rect(margins[0], margins[1], margins[2], margins[1] + fontManager().getComfortaa().getHeight() + 6, new Color(0, 0, 0, 140).getRGB());

        //Modules-Background
        RenderUtils.rect(margins[0], margins[1], mc.fontRendererObj.getStringWidth(title) + 40, sr.getScaledHeight() - margins[1], new Color(0, 0, 0, 140).getRGB());

        //Title Text
        GlStateManager.pushMatrix();
        GlStateManager.disableBlend();

        fontManager().getComfortaa().drawStringWithShadow(title, 35, 35, -1);

        GlStateManager.enableBlend();
        GlStateManager.popMatrix();

        //Rendering the Modules

        int y = 0;
        for (Module m : AlbinoClient.instance.modules) {
            if (m instanceof ClickGUI) continue;
            double mY = 55 + y;
            fontManager().getComfortaa().drawStringWithShadow(m.getName(), 36, mY, -1);
            float height = fontManager().getComfortaa().getStringHeight(m.getName());
            double width = fontManager().getComfortaa().getStringWidth(m.getName());
            map.put(m, new BoundingBox(36, 36 + width, mY, mY + height, 0, 0));
            y += 15;
        }
        // Rendering settings

        if (selected != null) {
            int y1 = margins[1] * 2;
            System.out.println(selected.settings.size());
            for (ModuleSetting<?> set : selected.settings) {
                set.render(this, margins[0] * 4, y1, 20, 20);
                y1 += 20;
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        int y = 0;
        Predicate<Module> pred = m -> map.containsKey(m) && map.get(m).contains(mouseX, mouseY);
        switch (mouseButton) {
            case 0:
                for (Module m : AlbinoClient.instance.modules) {
                    if (pred.test(m)) {
                        m.toggle();
                    }
                    if (m.isEnabled()) {
                        m.setY(y);
                        y += 15;
                    }
                }
                break;
            case 1:
                Optional<Module> optional = AlbinoClient.instance.modules.stream().filter(pred).findAny();
                System.out.println(optional.isPresent());
                optional.ifPresent(module -> this.selected = module);
                break;
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void addTextField(GuiTextField f) {
        this.fields.add(f);
    }
}
