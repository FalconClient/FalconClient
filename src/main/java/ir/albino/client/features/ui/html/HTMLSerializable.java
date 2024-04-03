package ir.albino.client.features.ui.html;

import java.io.Serializable;
import java.lang.reflect.Field;

public class HTMLSerializable {
    public String serialize() throws IllegalAccessException {
        Field[] fields = getClass().getDeclaredFields();
        StringBuilder builder = new StringBuilder();
        for (Field f : fields) {
            String n = f.getName();
            String s = f.get(this).toString();

            if (f.getType().getSuperclass() != null && f.getType().getSuperclass().equals(HTMLSerializable.class)) {
                s = ((HTMLSerializable) f.get(this)).serialize();
            }
            builder.append(String.format("<%s>%s</%s>", n, s, n));
        }
        return String.format("<%s>%s</%s>", getClass().getName(), builder, getClass().getName());
    }
}
