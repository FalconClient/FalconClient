package ir.albino.client.features.ui.html.modules;

import ir.albino.client.features.ui.html.annotations.Event;
import ir.albino.client.features.ui.html.annotations.HTMLMap;
import ir.albino.client.features.ui.html.serialize.HTMLSerializable;
import net.minecraft.client.gui.GuiButton;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Map;


public class HTMLButton extends GuiButton implements HTMLSerializable {
    @HTMLMap(serializeFunction = "exp", deserializeFunction = "expSerialize")
    public ExpressionBuilder heightExp;
    @HTMLMap(serializeFunction = "exp", deserializeFunction = "expSerialize")
    public ExpressionBuilder widthExp;
    @HTMLMap(serializeFunction = "exp", deserializeFunction = "expSerialize")
    public ExpressionBuilder yExp;
    @HTMLMap(serializeFunction = "exp", deserializeFunction = "expSerialize")
    public ExpressionBuilder xExp;

    public HTMLButton(int buttonId, String x, String y, String buttonText) {
        this(buttonId, 0, 0, buttonText);
        this.xExp = new ExpressionBuilder(x);
        this.yExp = new ExpressionBuilder(y);
    }

    public HTMLButton initButton(Map<String, Double> map) {
        if (xExp != null)
            xPosition = (int) xExp.build().setVariables(map).evaluate();
        if (yExp != null)
            this.yPosition = (int) yExp.build().setVariables(map).evaluate();
        if (widthExp != null)
            this.setWidth((int) widthExp.build().setVariables(map).evaluate());
        if (heightExp != null)
            this.setHeight((int) heightExp.build().setVariables(map).evaluate());
        return this;
    }

    private HTMLButton(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, buttonText);
    }


    public HTMLButton(int id, String x, String y, String width, String height, String text) {
        this(id, x, y, text);
        this.widthExp = new ExpressionBuilder(width);
        this.heightExp = new ExpressionBuilder(height);

    }


    @Event(eventType = Event.Type.AFTER_SERIALIZE)
    public void onSerializeEvent() {
        this.xExp = xExp.variables("height", "width");
        this.yExp = yExp.variables("height", "width");
        if (widthExp != null)
            widthExp = widthExp.variables("height", "width");
        if (heightExp != null)
            this.heightExp = heightExp.variables("height", "width");

    }
}
