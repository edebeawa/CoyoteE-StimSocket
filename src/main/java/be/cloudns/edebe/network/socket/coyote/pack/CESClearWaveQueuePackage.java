package be.cloudns.edebe.network.socket.coyote.pack;

import be.cloudns.edebe.network.socket.coyote.CESChannel;

public class CESClearWaveQueuePackage extends CESNetworkPackage {
    public CESClearWaveQueuePackage(CESChannel channel) {
        super(4);
        map.put("message", "clear-" + channel.getIndex());
    }
}