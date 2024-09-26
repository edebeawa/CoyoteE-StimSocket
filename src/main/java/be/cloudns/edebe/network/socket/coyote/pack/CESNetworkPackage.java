package be.cloudns.edebe.network.socket.coyote.pack;

import org.jetbrains.annotations.Nullable;
import be.cloudns.edebe.network.socket.coyote.GsonProvider;

import java.util.LinkedHashMap;
import java.util.Map;

public class CESNetworkPackage {
    protected final Map<String, Object> map = new LinkedHashMap<>();

    public CESNetworkPackage(@Nullable Object type) {
        map.put("type", type != null ? type : "msg");
    }

    public String toJson(String clientId, String targetId) {
        map.put("clientId", clientId);
        map.put("targetId", targetId);
        return GsonProvider.GSON.toJson(map, Map.class);
    }
}