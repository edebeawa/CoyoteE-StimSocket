package be.cloudns.edebe.network.socket.coyote.pack;

import be.cloudns.edebe.network.socket.coyote.CESChannel;

public class CESSetMaxStrengthPackage extends CESNetworkPackage {
    public CESSetMaxStrengthPackage(CESChannel channel, int value) {
        super(4);
        map.put("message", "strength-" + channel.getIndex() + "+2+" + value);
    }
}