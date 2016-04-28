package app;
import vendor.de.roderick.weberknecht.WebSocket;
import vendor.de.roderick.weberknecht.WebSocketEventHandler;
import vendor.de.roderick.weberknecht.WebSocketException;
import vendor.de.roderick.weberknecht.WebSocketMessage;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;



/** This example demonstrates how to create a websocket connection to a server. Only the most important callbacks are overloaded. */
public class WebSocketClientApp {





    public static void main( String[] args ) throws URISyntaxException {
        try {
            URI url = new URI("ws://127.0.0.1:8080/test");
            WebSocket websocket = new WebSocket(url);

            // Register Event Handlers
            websocket.setEventHandler(new WebSocketEventHandler() {
                public void onOpen()
                {
                    System.out.println("--open");
                }

                public void onMessage(WebSocketMessage message)
                {
                    System.out.println("--received message: " + message.getText());
                }

                @Override
                public void onError(IOException exception) {
                    
                }

                public void onClose()
                {
                    System.out.println("--close");
                }

                public void onPing() {}
                public void onPong() {}
            });

            // Establish WebSocket Connection
            websocket.connect();

            // Send UTF-8 Text
            websocket.send("hello world");

            // Close WebSocket Connection
            websocket.close();
        }
        catch (WebSocketException wse) {
            wse.printStackTrace();
        }
        catch (URISyntaxException use) {
            use.printStackTrace();
        }
    }

}