package ir.albino.client.features.ui.html.serialize;

import ir.albino.client.features.ui.html.annotations.Event;
import ir.albino.client.features.ui.html.annotations.HTMLMap;
import ir.albino.client.utils.Common;
import lombok.Getter;
import net.minecraft.util.ResourceLocation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;


@Getter
public class HTMLParser {

    public <T> T parse(String path, T obj) throws IOException {
        return this.parse(Jsoup.parse(new File(path)), obj);
    }

    public <T> T parse(File file, T obj) throws IOException {
        return this.parse(Jsoup.parse(file), obj);
    }

    public <T> T parse(Element element, T obj) {
        boolean condition = obj.getClass().isAnnotationPresent(Event.class);
        List<Method> pre = new ArrayList<>();
        List<Method> after = new ArrayList<>();
        if (condition) {
            pre = Common.getMethodsAnnotation(obj.getClass(), Event.class).stream().filter(m -> m.getAnnotation(Event.class).eventType().equals(Event.Type.BEFORE_SERIALIZE)).collect(Collectors.toList());
            after = Common.getMethodsAnnotation(obj.getClass(), Event.class).stream().filter(m -> m.getAnnotation(Event.class).eventType().equals(Event.Type.AFTER_SERIALIZE)).collect(Collectors.toList());
        }
        for (Method method : pre) {
            try {
                method.setAccessible(true);
                method.invoke(obj);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        for (Field f : Common.getFieldsAnnotation(obj.getClass(), HTMLMap.class)) {
            HTMLMap map = f.getAnnotation(HTMLMap.class);
            String id = f.getName();
            if (!map.id().isEmpty()) id = map.id();
            Element e = element.getElementById(id);
            try {
                if (!map.deserializeFunction().isEmpty()) this.deserializeField(f, obj, e);
                else this.setFieldValue(f, obj, e);
            } catch (IllegalAccessException | InstantiationException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        for (Method method : after) {
            try {
                method.setAccessible(true);
                method.invoke(obj);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return obj;
    }

    //FIXME: Fix parsing html to class exception is raising from this method
    private void setFieldValue(Field f, Object obj, Element v) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        Object fObj = f.get(obj);
        if (fObj instanceof Integer) f.set(obj, Integer.parseInt(v.text()));
        else if (fObj instanceof Boolean) f.set(obj, Boolean.parseBoolean(v.text()));
        else if (fObj instanceof HTMLSerializable) {
            f.set(obj, parse(v, fObj));
        } else if (fObj instanceof Enum<?>) {
            f.set(obj, Enum.valueOf(((Enum<?>) fObj).getClass(), v.text()));
        } else f.set(obj, v.text());
    }

    private void deserializeField(Field f, Object obj, Element e) {
        HTMLMap map = f.getAnnotation(HTMLMap.class);
        try {
            f.set(obj, map.deserialize().getMethod(map.deserializeFunction(), String.class).invoke(null, e.text()));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        }
    }

    public <T> T parse(ResourceLocation res, T obj) throws IOException {
        return this.parse(String.format("assets/%s/%s", res.getResourceDomain(), res.getResourcePath()), obj);
    }

}
