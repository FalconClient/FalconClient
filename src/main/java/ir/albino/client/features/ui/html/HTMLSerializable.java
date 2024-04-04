package ir.albino.client.features.ui.html;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import java.io.Serializable;
import java.lang.reflect.Field;

public class HTMLSerializable {
    public String serialize() throws IllegalAccessException {
        Field[] fields = getClass().getDeclaredFields();
        StringBuilder builder = new StringBuilder();
        for (Field f : fields) {
            String n = f.getType().getName();
            String s = f.get(this).toString();

            if (f.getType().getSuperclass() != null && f.getType().getSuperclass().equals(HTMLSerializable.class)) {
                s = ((HTMLSerializable) f.get(this)).serialize();
            }
            builder.append(String.format("<%s id=\"%s\">%s</%s>", n, f.getName(), s, n));
        }
        return String.format("<%s>%s</%s>", getClass().getName(), builder, getClass().getName());
    }



    public static HTMLSerializable deserialize(Element e, Class<? extends HTMLSerializable> clazz) throws InstantiationException, IllegalAccessException {
        HTMLSerializable obj = clazz.newInstance();

        return new HTMLParser<>(e, obj).getObj();
    }
}
