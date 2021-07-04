package sample;

import java.util.ArrayList;

public class SessionsManager {

    private final ArrayList<ClientSession> sessions = new ArrayList<ClientSession>();

    public SessionsManager() {}

    public synchronized void addSession(ClientSession session) {
        sessions.add(session);
    }

    public synchronized void removeSession(ClientSession session) {
        sessions.remove(session);
    }
}