package be.cloudns.edebe.network.socket.coyote.pack;

import be.cloudns.edebe.network.socket.coyote.GsonProvider;

import java.util.List;

public class CESWaveOutputPackage extends CESNetworkPackage {
    private CESWaveOutputPackage(String waveA, String waveB, int timeA, int timeB) {
        super("clientMsg");
        map.put("message", "A:" + waveA);
        map.put("message2", "B:" + waveB);
        map.put("time1", timeA);
        map.put("time2", timeB);
    }

    public CESWaveOutputPackage(String[] waveA, String[] waveB, int timeA, int timeB) {
        this(GsonProvider.GSON.toJson(waveA, String[].class), GsonProvider.GSON.toJson(waveB, String[].class), timeA, timeB);
    }

    public CESWaveOutputPackage(List<String> waveA, List<String> waveB, int timeA, int timeB) {
        this(GsonProvider.GSON.toJson(waveA, List.class), GsonProvider.GSON.toJson(waveB, List.class), timeA, timeB);
    }
}