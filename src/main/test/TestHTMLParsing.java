import org.junit.Test;
import test.ExampleHTML;

public class TestHTMLParsing {
    @Test
    public void testSerialize(){
        try {
            System.out.println(new ExampleHTML().serialize());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
