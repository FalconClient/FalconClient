package ir.albino.client.features.ui.html;

import lombok.Getter;
import net.minecraft.util.ResourceLocation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;

@Getter
public class HTMLParser<T> {
    public final T obj;

    public HTMLParser(String path, T obj) {
        Document doc = null;
        try {
            doc = Jsoup.parse(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.obj = obj;
        for (Element e : doc.getAllElements()) {
            try {
                if (!e.id().isEmpty()) {
                    Field f = obj.getClass().getDeclaredField(e.id());
                    this.setFieldValue(f, obj, e.val());
                }
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                System.out.println(e.id());
                throw new RuntimeException(ex);
            }
        }
    }

    private void setFieldValue(Field f, Object inst, String v) throws IllegalAccessException {
        if (obj instanceof Integer) {
            f.set(inst, Integer.valueOf(v));
        }

    }

    public HTMLParser(ResourceLocation res, T obj) {
        this(String.format("assets/%s/%s", res.getResourceDomain(), res.getResourcePath()), obj);
    }

}
