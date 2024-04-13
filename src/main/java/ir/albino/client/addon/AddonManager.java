package ir.albino.client.addon;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import ir.albino.client.AlbinoClient;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class AddonManager {
    private final Map<String, AlbinoAddon> addons = new HashMap<>();

    /**
     * Loads an addon from file.
     *
     * @param file the addon file. it should already exist and be a valid albino addon file
     * @return loaded addon
     */
    public AlbinoAddon loadAddon(File file) throws IOException {
        @Cleanup
        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()});
        YAMLMapper mapper = new YAMLMapper();
        AlbinoAddon.AddonConfiguration config = mapper.readValue(loader.findResource("addon.yml"), AlbinoAddon.AddonConfiguration.class);
        AlbinoAddon addon = null;
        try {
            addon = (AlbinoAddon) Class.forName(config.addonName, true, loader).getConstructors()[0].newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        addon.onLoad(AlbinoClient.instance);
        addons.put(config.addonName, addon);
        return addon;
    }

    /**
     * Unloads the specified addon
     *
     * @param name specified addon name (Should be already loaded up)
     */
    public void unloadAddon(String name) {
        AlbinoAddon ad = addons.get(name);
        ad.onUnload(AlbinoClient.instance);
        addons.remove(name);
    }

    /**
     * Gets the specified addon by its name
     *
     * @param addonName the addon's name.
     * @return the specified addon if its already loaded.
     */
    public AlbinoAddon getAddon(String addonName) {
        return addons.get(addonName);
    }


}
