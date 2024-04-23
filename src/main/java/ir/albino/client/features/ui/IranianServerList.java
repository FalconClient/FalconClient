package ir.albino.client.features.ui;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.json.JsonMapper;
import ir.albino.client.AlbinoClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.crash.CrashReport;
import net.minecraft.util.ReportedException;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class IranianServerList extends ServerList {
    public IranianServerList(Minecraft mcIn) {
        super(mcIn);
    }

    @Override
    public void loadServerList() {
        if (!servers.isEmpty()) return;
        AlbinoClient client = AlbinoClient.instance;
        client.executorService.submit(loadTask());
    }

    private Runnable loadTask() {
        return () -> {
            JsonMapper map = new JsonMapper();
            try {
                URL url = new URL("https://mctools.ir/api/v1/servers");
                PrivateServerData[] servers = map.readValue(url, PrivateServerData[].class);
                IranianServerList.this.servers = Arrays.stream(servers).map(PrivateServerData::asServerData).collect(Collectors.toList());
            } catch (IOException e) {
                throw new ReportedException(new CrashReport("Couldnt load iranian servers", e));
            }
        };
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class PrivateServerData {
        public PrivateServerData() {

        }

        public String name;
        public String ip;

        public ServerData asServerData() {
            return new ServerData(name, ip, false);
        }
    }
}
