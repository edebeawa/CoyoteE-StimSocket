package be.cloudns.edebe.network.socket.coyote;

import java.util.Arrays;

public class CESStrength {
    private static final String STRENGTH = "strength";
    private static final int STRENGTH_LENGTH = STRENGTH.length();
    private static final String COMMA = ",";
    private static final int COMMA_LENGTH = COMMA.length();
    private final Integer[] strength;

    public CESStrength() {
        strength = new Integer[]{0, 0, 0, 0};
    }

    public CESStrength(String message) {
        StringBuilder builder = new StringBuilder();
        for (char c : message.substring(message.indexOf(STRENGTH) + STRENGTH_LENGTH).toCharArray()) {
            if (c == '+' || c == '-')
                builder.append(COMMA);
            builder.append(c);
        }
        strength = Arrays.stream(builder.substring(COMMA_LENGTH).split(COMMA)).map((string) -> Math.abs(Integer.parseInt(string))).toArray(Integer[]::new);
    }

    public int getCurrentStrength(CESChannel channel) {
        return strength[channel.getIndex() - 1];
    }

    public int getStrengthUpperLimit(CESChannel channel) {
        return strength[channel.getIndex() + 1];
    }
}