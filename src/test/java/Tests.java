import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ir.albino.client.account.AltManager;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Tests {
    // Result: succeed
    @Test
    public void unicodeTest() {
        char a = 0;
        System.out.println(a);
    }

    // Final Result: succeed
    @Test
    public void urlTest() throws IOException {
        Type listType = new TypeToken<ArrayList<Servers.Server>>() {
        }.getType();
        Reader reader = new InputStreamReader(new URL("https://mctools.ir/api/v1/servers").openStream());
        ArrayList<Servers.Server> servers = new Gson().fromJson(reader, listType);
        System.out.println(servers.get(0).name);
    }

    @Test
    public void saveAltManager(){
        AltManager manager = new AltManager();
        manager.addOfflineSession("User", UUID.randomUUID());
        manager.addOfflineSession("Mmd4J", UUID.randomUUID());
        manager.addOfflineSession("GaMeIsNtOvEr", UUID.randomUUID());
        manager.save();
    }
}
