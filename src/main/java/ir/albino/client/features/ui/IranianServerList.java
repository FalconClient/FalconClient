package ir.albino.client.features.ui;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.Cleanup;
import lombok.SneakyThrows;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.crash.CrashReport;
import net.minecraft.util.ReportedException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class IranianServerList extends ServerList {
    public IranianServerList(Minecraft mcIn) {
        super(mcIn);
    }

    @Override
    public void loadServerList() {
        if (!servers.isEmpty()) return;
        JsonMapper map = new JsonMapper();
        try {
            URL url = new URL("https://mctools.ir/api/v1/servers");
            List<PrivateServerData> servers = Arrays.asList(map.readValue(url, PrivateServerData[].class));
            this.servers = servers.stream().map(PrivateServerData::asServerData).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

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
