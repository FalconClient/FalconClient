package ir.albino.client.features.ui.html.modules;

import lombok.experimental.UtilityClass;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.lang.reflect.Field;

@UtilityClass
public class SerializerUtils {
    public ExpressionBuilder exp(String exp) {
        return new ExpressionBuilder(exp);
    }

    public String expSerialize(ExpressionBuilder exp) {
        try {
            Field f = exp.getClass().getDeclaredField("expression");
            f.setAccessible(true);
            return (String) f.get(exp);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
