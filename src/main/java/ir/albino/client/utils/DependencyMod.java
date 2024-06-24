package ir.albino.client.utils;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.extern.jackson.Jacksonized;
import net.minecraft.crash.CrashReport;
import net.minecraft.util.ReportedException;
import org.apache.commons.lang3.SystemUtils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DependencyMod {
    List<Dependency> dependencies = new ArrayList<>();

    public void downloadDeps() {
        for (Dependency dep : dependencies) {
            if (!dep.url.isEmpty()) {
                try {
                    URL url = new URL(dep.url);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(String.format("Failed to download dependency named %s ." +
                            "\n url: %s", dep.name, dep.url));
                }
            } else if (SystemUtils.IS_OS_WINDOWS) {
                try {
                    URL url = new URL(dep.urlWindows);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(String.format("Failed to download dependency named %s ." +
                            "\n url: %s", dep.name, dep.urlWindows));
                }
            }else{
                
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    protected static class Dependency {
        public String name = "";
        public String url = "";
        public String urlWindows = "";
        public String urlLinux = "";

    }
}
