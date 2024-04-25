package ir.albino.client.utils;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.extern.jackson.Jacksonized;
import org.json.JSONPropertyIgnore;

import java.util.ArrayList;
import java.util.List;

public class DependencyMod {
    List<Dependency> dependencies = new ArrayList<>();

    public void downloadDeps() {
        for (Dependency dep : dependencies) {
            if (!dep.url.isEmpty()) {

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
