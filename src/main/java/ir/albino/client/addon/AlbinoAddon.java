package ir.albino.client.addon;

import ir.albino.client.AlbinoClient;
import ir.albino.client.features.modules.Module;
import lombok.Getter;

import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

@Getter
public abstract class AlbinoAddon {
    private URLClassLoader loader;
    private AddonConfiguration configuration;

    abstract void onLoad(AlbinoClient client);

    abstract void onUnload(AlbinoClient client);

    abstract List<Module> modules();

    protected final static class AddonConfiguration {
        public String addonName = "";
        public String addonVersion = "";
        public String addonDescription = "";
        public String addonWebsite = "";
        public String mainClass = "";
    }
}
