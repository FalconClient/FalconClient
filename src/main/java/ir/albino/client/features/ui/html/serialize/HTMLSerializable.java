package ir.albino.client.features.ui.html.serialize;

import ir.albino.client.features.ui.html.annotations.HTMLMap;
import ir.albino.client.utils.Common;
import lombok.Cleanup;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public interface HTMLSerializable {
    default String serialize() throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        for (Field f : Common.getFieldsAnnotation(this.getClass(), HTMLMap.class)) {
            f.setAccessible(true);
            HTMLMap map = f.getAnnotation(HTMLMap.class);
            String s;
            if (f.get(this) == null) {
                s = map.defaultValue();
            } else {
                try {
                    if (!map.deserializeFunction().isEmpty())
                        s = (String) map.deserialize().getDeclaredMethod(map.deserializeFunction(), f.getType()).invoke(null, f.get(this));
                    else s = f.get(this).toString();
                } catch (InvocationTargetException | NoSuchMethodException e) {
                    System.out.println(map.deserializeFunction());
                    throw new RuntimeException(e);
                }
                if (f.get(this) instanceof HTMLSerializable)
                    s = ((HTMLSerializable) f.get(this)).serialize();
            }
            String id = f.getName();
            if (!map.id().isEmpty()) id = map.id();
            String html = String.format("<%s id=\"%s\">%s</%s>", map.customTag(), id, s, map.customTag());
            builder.append(html);
        }
        return builder.toString();
    }


    static HTMLSerializable deserialize(Element e, Class<? extends HTMLSerializable> clazz) throws InstantiationException, IllegalAccessException {
        HTMLSerializable obj = clazz.newInstance();
        return new HTMLParser().parse(e, obj);
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

    default File getFile() {
        return new File(Common.getGamePath(), String.format("%s.html", getClass().getSimpleName().toLowerCase()));
    }


}
