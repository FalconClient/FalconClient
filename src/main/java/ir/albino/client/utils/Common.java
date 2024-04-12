package ir.albino.client.utils;

import ir.albino.client.features.ui.MainMenu;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class Common {
    public File getGamePath() {
        if (SystemUtils.IS_OS_WINDOWS) return new File(System.getenv("APPDATA") + "/AlbinoClient/");
        return new File("~/AlbinoClient/");
    }

    public File getGameAssetsPath() {
        return new File(getGamePath(), "assets");
    }

    public File getModulesPath() {
        return new File(getGamePath(), "modules");
    }

    public <K, V> Map<K, V> ofEntries(Collection<Map.Entry<K, V>> c) {
        Map<K, V> map = new HashMap<>();
        for (Map.Entry<K, V> entry : c) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    @SafeVarargs
    public <K, V> Map<K, V> ofEntries(Map.Entry<K, V>... entries) {
        Map<K, V> map = new HashMap<>();
        for (Map.Entry<K, V> entry : entries) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    public List<Field> getFieldsAnnotation(Class<?> obj, Class<? extends Annotation> annotation) {
        return Arrays.stream(obj.getDeclaredFields()).filter(f -> f.isAnnotationPresent(annotation)).collect(Collectors.toList());
    }

    public List<Method> getMethodsAnnotation(Class<?> obj, Class<? extends Annotation> annotation) {
        return Arrays.stream(obj.getDeclaredMethods()).filter(m -> m.isAnnotationPresent(annotation)).collect(Collectors.toList());
    }

    public static String getScreenURI(Class<?> obj) {
        return new File(getScreensDirectory(), obj.getSimpleName().toLowerCase()).getPath();
    }

    public static File getScreensDirectory() {
        File screens = new File(getGamePath(), "screens");
        if (!screens.exists()) screens.mkdirs();
        return screens;
    }
}
