package ir.albino.client.features.modules;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.stream.JsonReader;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import ir.albino.client.AlbinoClient;
import ir.albino.client.features.modules.configuration.ModuleTheme;
import ir.albino.client.utils.Common;
import lombok.SneakyThrows;
import lombok.val;

import java.io.File;

public class ModuleManager {

    private ModuleTheme currentTheme;

    @SneakyThrows
    public void initModules() {
        if (!Common.getModulesPath().exists()) Common.getModulesPath().mkdirs();
        try (ScanResult scanResult = new ClassGraph().enableAllInfo().scan()) {
            for (ClassInfo clz : scanResult.getClassesWithAnnotation(ModuleInfo.class)) {

                val loadClass = clz.loadClass();
                val annotation = loadClass.getAnnotation(ModuleInfo.class);
                val catFile = new File(Common.getModulesPath(), String.format("%s.json", annotation.module()));
                final Module module;
                JsonMapper map = new JsonMapper();
                if (catFile.exists()) {
                    module = (Module) map.readValue(catFile, loadClass);
                } else module = (Module) loadClass.getConstructor().newInstance();
                module.setName(annotation.module());
                module.setDescription(annotation.description());
                module.setVersion(annotation.version());
                module.setDraggable(annotation.draggable());
                module.onInit();

                AlbinoClient.instance.getLogger().info(String.format("Module Loaded %s - %s", annotation.module(), annotation.version()));
                map.writeValue(catFile, module);
                AlbinoClient.instance.modules.add(module);
            }
        }
    }

    public Module getModuleByName(String name) {
        val module = AlbinoClient.instance.modules.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst();
        return module.orElse(null);
    }

    public ModuleTheme getModuleTheme() {
        if (currentTheme == null) {
            currentTheme = new ModuleTheme();
        }
        return currentTheme;
    }

    public void renderModuleSettings() {

    }
}
