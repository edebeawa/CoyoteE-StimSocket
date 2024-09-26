package be.cloudns.edebe.network.socket.coyote.pack;

import lombok.AllArgsConstructor;
import be.cloudns.edebe.network.socket.coyote.CESChannel;

public class CESSetStrengthPackage extends CESNetworkPackage {
    public CESSetStrengthPackage(CESChannel channel, Operate operate, int value) {
        super(operate.type);
        map.put("strength", value);
        map.put("message", "set channel");
        map.put("channel", channel.getIndex());
    }

    @AllArgsConstructor
    public enum Operate {
        SHRINK(1),
        GROW(2),
        SET(3);

        private final int type;
    }
}