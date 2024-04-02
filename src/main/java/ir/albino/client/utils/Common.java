package ir.albino.client.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class Common {
    public File getGamePath() {
        if (SystemUtils.IS_OS_WINDOWS)
            return new File(System.getenv("APPDATA") + "/AlbinoClient/");
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
}
