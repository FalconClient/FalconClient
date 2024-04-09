package ir.albino.client.features.ui.html.modules;

import ir.albino.client.features.ui.html.annotations.HTMLAttributes;
import ir.albino.client.features.ui.html.annotations.HTMLIgnore;
import ir.albino.client.features.ui.html.serialize.HTMLSerializable;
import ir.albino.client.features.ui.html.annotations.ConstructorInjection;
import net.minecraft.client.gui.GuiButton;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Map;


public class HTMLButton extends GuiButton implements HTMLSerializable {
    @HTMLIgnore
    public Expression heightExp;
    @HTMLIgnore
    public Expression widthExp;
    @HTMLIgnore
    public Expression yExp;
    @HTMLIgnore
    public Expression xExp;
    public String xStr = "";
    public String yStr = "";
    public String widthStr = "";
    public String heightStr = "";

    @ConstructorInjection(attrs = {"buttonId", "x", "y"})
    public HTMLButton(int buttonId, String x, String y, String buttonText) {
        this(buttonId, 0, 0, buttonText);
        this.xStr = x;
        this.yStr = y;
        this.xExp = new ExpressionBuilder(x).variables("width", "height").build();
        this.yExp = new ExpressionBuilder(y).variables("width", "height").build();
    }

    public HTMLButton initButton(Map<String, Double> map) {
        if (xExp != null)
            xPosition = (int) xExp.setVariables(map).evaluate();
        if (yExp != null)
            this.yPosition = (int) yExp.setVariables(map).evaluate();
        if (widthExp != null)
            this.setWidth((int) widthExp.setVariables(map).evaluate());
        if (heightExp != null)
            this.setHeight((int) heightExp.setVariables(map).evaluate());
        return this;
    }

    private HTMLButton(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, buttonText);
    }


    @ConstructorInjection(attrs = {"id", "x", "y", "width", "height", "buttonText"})
    public HTMLButton(int id, String x, String y, String width, String height, String text) {
        this(id, x, y, text);
        this.widthExp = new ExpressionBuilder(width).variables("width", "height").build();
        this.heightExp = new ExpressionBuilder(height).variables("width", "height").build();
        this.widthStr = width;
        this.heightStr = height;
    }


    @Override
    public void onSerialize() {
        this.xExp = new ExpressionBuilder(xStr).variables("width", "height").build();
        this.yExp = new ExpressionBuilder(yStr).variables("width", "height").build();
        this.widthExp = new ExpressionBuilder(widthStr).variables("width", "height").build();
        this.heightExp = new ExpressionBuilder(heightStr).variables("width", "height").build();
    }
}
