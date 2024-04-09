package ir.albino.client.features.ui.html.modules;

import ir.albino.client.features.ui.html.serialize.HTMLSerializable;
import net.minecraft.util.ResourceLocation;
import net.objecthunter.exp4j.Expression;

public interface HTMLItem extends HTMLSerializable {
    Expression getX();

    Expression getY();

    Expression getWidth();

    Expression getHeight();

}
