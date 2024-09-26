package be.cloudns.edebe.network.socket.coyote;

import lombok.Getter;
import lombok.Setter;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import be.cloudns.edebe.network.socket.coyote.pack.CESNetworkPackage;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public abstract class CoyoteEStimWebSocketClient extends WebSocketClient {
    private final String uri;
    @Setter
    @Getter
    private String clientIdCache = "";
    @Setter
    @Getter
    private String targetIdCache = "";

    public CoyoteEStimWebSocketClient(String address) throws URISyntaxException {
        super(new URI(newShortCoyoteEStimURIString(address)));
        uri = newLongCoyoteEStimURIString(address);
    }

    public static String newShortCoyoteEStimURIString(String address) {
        return "wss://ws.dungeon-lab.cn/" + address + "/";
    }

    public static String newLongCoyoteEStimURIString(String address) {
        return "https://www.dungeon-lab.com/app-download.php#DGLAB-SOCKET#" + newShortCoyoteEStimURIString(address);
    }

    public String getQrCodeDataURI() {
        return uri + clientIdCache;
    }

    public void onOpen(ServerHandshake handshake) {}

    @SuppressWarnings("unchecked")
    public void onMessage(String message) {
        Map<String, Object> map = GsonProvider.GSON.fromJson(message, Map.class);
        String targetId = (String) map.get("targetId");
        String targetMessage = (String) map.get("message");
        switch ((String) map.get("type")) {
            case "bind":
                String clientId = (String) map.get("clientId");
                onCoyoteEStimBindMessage(!targetId.isEmpty(), message, targetId, clientId, targetMessage);
                break;
            case "break":
                onCoyoteEStimBreakMessage(message, targetId, targetMessage);
                break;
            case "error":
                onCoyoteEStimErrorMessage(message, targetId, targetMessage);
                break;
            case "msg":
                if (targetMessage.contains("strength"))
                    onCoyoteEStimStrengthMessage(message, targetId, new CESStrength(targetMessage));
                else if (targetMessage.contains("feedback"))
                    onCoyoteEStimFeedbackMessage(message, targetId, targetMessage);
                break;
            case "heartbeat":
                onCoyoteEStimHeartbeatMessage(message, targetId, targetMessage);
                break;
            default:
                onCoyoteEStimUnknownMessage(message, targetId, targetMessage);
                break;
        }
    }

    public void onClose(int code, String reason, boolean remote) {}

    public void onError(Exception exception) {}

    public void send(CESNetworkPackage pack) {
        send(pack.toJson(clientIdCache, targetIdCache));
    }

    public abstract void onCoyoteEStimBindMessage(boolean initialize, String message, String targetId, String clientId, String targetMessage);

    public abstract void onCoyoteEStimBreakMessage(String message, String targetId, String targetMessage);

    public abstract void onCoyoteEStimErrorMessage(String message, String targetId, String targetMessage);

    public abstract void onCoyoteEStimStrengthMessage(String message, String targetId, CESStrength strength);

    public abstract void onCoyoteEStimFeedbackMessage(String message, String targetId, String targetMessage);

    public abstract void onCoyoteEStimHeartbeatMessage(String message, String targetId, String targetMessage);

    public abstract void onCoyoteEStimUnknownMessage(String message, String targetId, String targetMessage);
}