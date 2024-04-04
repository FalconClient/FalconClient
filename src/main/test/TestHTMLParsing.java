import ir.albino.client.AlbinoClient;
import ir.albino.client.features.ui.html.HTMLParser;
import lombok.Cleanup;
import lombok.val;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import test.ExampleHTML;

import javax.swing.text.html.HTML;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class TestHTMLParsing {
    @Test
    public void testSerialize() {
        try {
            String serialize = new ExampleHTML().serialize();
            File file = new File("test.html");

            @Cleanup
            FileWriter writer = new FileWriter(file);
            writer.write(serialize);

        } catch (IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDeserialize() {
        val obj = new HTMLParser<>("test.html", new ExampleHTML()).getObj();
        System.out.println(obj);
    }
}
