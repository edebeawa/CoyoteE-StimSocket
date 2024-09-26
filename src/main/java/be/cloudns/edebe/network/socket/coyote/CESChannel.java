package be.cloudns.edebe.network.socket.coyote;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CESChannel {
    A(1),
    B(2);

    @Getter
    private final int index;
}