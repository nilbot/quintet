package app;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * WSApp:
 */
public class WSApp extends WebSocketClient {
    public WSApp(URI serverURI) {
        super(serverURI,new Draft_17());
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        System.out.println("holyshit it connects?");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("you gave me this:"+message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("i don't know how to handle close but here is what" +
                " i got:");
        System.out.println("remote?: "+remote+", code: "+code+", reason: " +
                ""+reason);
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("and I don't know how to handle error");
    }

    public static void main(String[] args) {
        WSApp ws = null;
        try {
            ws = new WSApp(new URI("ws://127" +
                    ".0.0.1:8080/echo"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ws.connect();
        try {
            ws.send("Hello World".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
