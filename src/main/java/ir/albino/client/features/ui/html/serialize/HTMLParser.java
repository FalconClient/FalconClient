package ir.albino.client.features.ui.html.serialize;

import ir.albino.client.features.ui.html.annotations.HTMLIgnore;
import lombok.Cleanup;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Stream;


@Getter
public class HTMLParser {

    public <T> T parse(String path, T obj) throws IOException {
        return this.parse(Jsoup.parse(new File(path)), obj);
    }

    public <T> T parse(File file, T obj) throws IOException {
        return this.parse(Jsoup.parse(file), obj);
    }

    public <T> T parse(Element element, T obj) {
        if (obj instanceof HTMLSerializable) {
            ((HTMLSerializable) obj).onPreSerialize();
        }
        Iterator<Element> it = element.getElementsByTag("field").iterator();
        while (it.hasNext()) {
            Element e = it.next();
            try {
                Field f = obj.getClass().getField(e.id());
                this.setFieldValue(f, obj, e);
                for (int i = 0; i < e.childrenSize(); i++)
                    if (it.hasNext()) it.next().id();

            } catch (IllegalAccessException | ClassNotFoundException | InstantiationException |
                     NoSuchFieldException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (obj instanceof HTMLSerializable) {
            ((HTMLSerializable) obj).onSerialize();
        }
        return obj;
    }

    public <T> T parseChildren(Element parent, T obj) {
        if (obj instanceof HTMLSerializable) {
            ((HTMLSerializable) obj).onPreSerialize();
        }
        for (Element e : parent.children()) {
            try {
                Field f = obj.getClass().getField(e.id());
                this.setFieldValue(f, obj, e);
            } catch (IllegalAccessException | ClassNotFoundException | InstantiationException |
                     NoSuchFieldException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (obj instanceof HTMLSerializable) {
            ((HTMLSerializable) obj).onSerialize();
        }
        return obj;
    }

    private void setFieldValue(Field f, Object obj, Element v) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        Object fObj = f.get(obj);
        if (fObj instanceof Integer) f.set(obj, Integer.parseInt(v.text()));
        else if (fObj instanceof Boolean) f.set(obj, Boolean.parseBoolean(v.text()));
        else if (fObj instanceof HTMLSerializable) {
            f.set(obj, parseChildren(v, fObj));
        } else if (fObj instanceof Enum<?>) {
            f.set(obj, Enum.valueOf(((Enum<?>) fObj).getClass(), v.text()));
        } else {
            f.set(obj, v.text());
        }
    }

    public <T> T parse(ResourceLocation res, T obj) throws IOException {
        return this.parse(String.format("assets/%s/%s", res.getResourceDomain(), res.getResourcePath()), obj);
    }

}
