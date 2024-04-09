import ir.albino.client.features.ui.MainMenu;
import ir.albino.client.features.ui.html.serialize.HTMLParser;
import lombok.Cleanup;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.junit.Test;
import test.ExampleHTML;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        ExampleHTML obj = null;
        try {
            obj = new HTMLParser().parse("test.html", new ExampleHTML());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(obj);
    }

    @Test
    public void testStringMath() {
        Expression exp = new ExpressionBuilder("4*5").build();
        System.out.println(exp.evaluate());
    }

    @Test
    public void testSavingMainMenuScreen() {
        try {
            new MainMenu().save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
