import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import net.minecraft.client.gui.GuiScreen;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UITests {
    @Test
    public void jacksonTest() throws IOException {
        XmlMapper map = XmlMapper.builder().build();
        map.writeValue(new File("test.xml"), new GuiScreen() {
        });
        GuiScreen screen = map.readValue(new File("test.xml"), GuiScreen.class);
        System.out.println(screen);
    }

    public static class Screen {
        public File background = new File("background.png");
        public List<Button> buttons = new ArrayList<>();

        public Screen() {
            buttons.add(new Button("hey", 3, 3, 3, 3));
            buttons.add(new Button("welcome", 20, 30, 50, 70));
        }
    }

    public static class Button {
        public Button(String text, int x, int y, int width, int height) {
            this.text = text;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public String text = "Salam";
        public int x = 3;
        public int y = 3;
        public int width = 3;
        public int height = 3;
    }
}
