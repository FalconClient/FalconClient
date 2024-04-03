package ir.albino.client.features.ui.html;

import net.minecraft.util.ResourceLocation;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.lang.reflect.Field;
import java.net.URI;

public class HTMLParser {
    public HTMLParser(ResourceLocation res, Class<?> clazz) {
        Document doc = new Document("");
        Object obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        for (Element e : doc.getAllElements()) {
            try {
                Field f = obj.getClass().getDeclaredField(e.id());
                f.set(e.id(), obj);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
