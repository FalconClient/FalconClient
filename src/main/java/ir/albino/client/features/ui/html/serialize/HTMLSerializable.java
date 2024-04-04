package ir.albino.client.features.ui.html.serialize;

import ir.albino.client.features.ui.html.annotations.HTMLIgnore;
import ir.albino.client.utils.Common;
import lombok.Cleanup;
import org.jsoup.nodes.Element;

import javax.swing.text.html.HTML;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

public interface HTMLSerializable {
    default String serialize() throws IllegalAccessException {
        Field[] fields = getClass().getDeclaredFields();
        StringBuilder builder = new StringBuilder();
        for (Field f : fields) {
            if (!f.isAnnotationPresent(HTMLIgnore.class) && f.get(this) != null) {
                String s = f.get(this).toString();
                if (f.get(this) instanceof HTMLSerializable) {
                    s = ((HTMLSerializable) f.get(this)).serialize();
                }
                builder.append(String.format("<field id=\"%s\" type=\"%s\">%s</field>", f.getName(), f.getType().getName(), s));
            }
        }
        return String.format("<value>%s</value>", builder);
    }


    static HTMLSerializable deserialize(Element e, Class<? extends HTMLSerializable> clazz) throws InstantiationException, IllegalAccessException {
        HTMLSerializable obj = clazz.newInstance();
        return new HTMLParser<>(e, obj).getObj();
    }

    default void save(String name) throws IOException {
        File file = new File(Common.getGamePath(), String.format("%s.html", name));
        if (!file.exists()) file.createNewFile();
        @Cleanup FileWriter writer = new FileWriter(file);
        try {
            writer.write(serialize());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    default void save() throws IOException {
        this.save(getClass().getSimpleName().toLowerCase());
    }
}
