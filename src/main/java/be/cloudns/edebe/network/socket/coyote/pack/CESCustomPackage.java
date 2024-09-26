package be.cloudns.edebe.network.socket.coyote.pack;

import org.jetbrains.annotations.Nullable;

public class CESCustomPackage extends CESNetworkPackage {
    public CESCustomPackage(@Nullable String type) {
        super(type);
    }

    public void add(String key, String value) {
        map.putIfAbsent(key, value);
    }
}