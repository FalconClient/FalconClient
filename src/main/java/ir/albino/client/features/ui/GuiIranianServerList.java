package ir.albino.client.features.ui;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ir.albino.client.server.ServerDetails;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GuiIranianServerList extends ServerList {

    private List<ServerDetails> servers;


    public GuiIranianServerList(Minecraft mcIn) {
        super(mcIn);
    }


    @Override
    public void loadServerList() {

        Type listType = new TypeToken<ArrayList<ServerDetails>>() {
        }.getType();
        try {
            Reader reader = new InputStreamReader(new URL("https://mctools.ir/api/v1/servers/").openStream());
            servers = new Gson().fromJson(reader, listType);
            reader.close();
        } catch (IOException e) {
            Minecraft.logger.warn("Couldn't track iranian mc servers from mctools. " + "\n Report the rest error to the AlbinoClient developers");
        }
        super.servers.clear();
        for (ServerDetails s : servers) {
            addServerData(new ServerData(s.name, s.ip, false));
        }
    }

    @Override
    public void saveServerList() {

    }
}
