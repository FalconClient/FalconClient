package ir.albino.client.features.ui.html;

import ir.albino.client.features.ui.html.annotations.HTMLIgnore;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import java.io.Serializable;
import java.lang.reflect.Field;

public class HTMLSerializable {
    public String serialize() throws IllegalAccessException {
        Field[] fields = getClass().getDeclaredFields();
        StringBuilder builder = new StringBuilder();
        for (Field f : fields) {
            if (!f.isAnnotationPresent(HTMLIgnore.class)) {
                String s = f.get(this).toString();
                if (f.getType().getSuperclass() != null && f.getType().getSuperclass().equals(HTMLSerializable.class)) {
                    s = ((HTMLSerializable) f.get(this)).serialize();
                }
                builder.append(String.format("<field id=\"%s\" type=\"%s\">%s</field>", f.getName(), f.getType().getName(), s));
            }
        }
        return String.format("<value>%s</value>", builder);
    }


    public static HTMLSerializable deserialize(Element e, Class<? extends HTMLSerializable> clazz) throws InstantiationException, IllegalAccessException {
        HTMLSerializable obj = clazz.newInstance();

        return new HTMLParser<>(e, obj).getObj();
    }
}
