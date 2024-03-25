package ir.albino.client.account;

import com.fasterxml.jackson.databind.json.JsonMapper;
import ir.albino.client.utils.Common;
import net.minecraft.util.Session;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AltManager {

    public Session currentSession = new Session("AlbinoPlayer","","","legacy");
    public Map<String, Session> sessions = new HashMap<>();

    public void addOfflineSession(String userName, UUID uuid) {
        Session session = new Session(userName, uuid.toString(), "0", Session.Type.LEGACY.name());
        sessions.put(userName, session);
        if (currentSession == null)
            currentSession = session;
    }

    public void save() {
        File file = new File(Common.getGamePath(), "users.json");
        try {
            new JsonMapper().writeValue(file, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void makeCurrentSession(Session session) {
        currentSession = session;
        sessions.put(session.getUsername(), session);
        System.out.println("Changed account to " + session.getUsername());
    }

}
