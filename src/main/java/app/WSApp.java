package app;


import logic.Solver;
import model.DataSource;
import org.glassfish.tyrus.client.ClientManager;
import presentation.InputMeta;
import presentation.Result;
import presentation.WSMessage;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * WSApp:
 */
public class WSApp {
    private static CountDownLatch messageLatch;
    private static final WSMessage SENT_MESSAGE = new WSMessage() {
        @Override
        public String toJson() {
            this.MessageType = "Hello World";
            return GSON.toJson(this,WSMessage.class);
        }
    };

    public static void main(String[] args) {
        Config config = new Config();
        config.GetConfig();

        // init data store
        DataSource repo = config.GetData();

        Solver solver = config.GetSolver();
        if (repo.Ready()) {
            solver.InjectData(repo);
        } else {
            System.err.println("Repo is not ready");
            return;
        }
        final InputMeta meta = repo.getMeta();
        final Result res = solver.Solve();

        helloWorld();

        sendMeta(meta);
        sendResult(res);
    }
    private static void sendMeta(final InputMeta meta) {
        messageLatch = new CountDownLatch(1);
        final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create
                ().build();
        ClientManager ws = ClientManager.createClient();
        try {
            ws.connectToServer(new Endpoint() {
                @Override
                public void onOpen(Session session, EndpointConfig config) {
                    session.addMessageHandler(new MessageHandler.Whole<String>() {
                        @Override
                        public void onMessage(String message) {
                            System.out.println("rx: " + message);
                            messageLatch.countDown();
                        }
                    });
                    session.getAsyncRemote().sendText(meta.toJson());
                }
            }, cec, new URI("ws://localhost:8080/meta"));
            messageLatch.await(100, TimeUnit.SECONDS);
        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void sendResult(final Result res) {
        messageLatch = new CountDownLatch(1);
        final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create
                ().build();
        ClientManager ws = ClientManager.createClient();
        try {
            ws.connectToServer(new Endpoint() {
                @Override
                public void onOpen(Session session, EndpointConfig config) {
                    session.addMessageHandler(new MessageHandler.Whole<String>() {
                        @Override
                        public void onMessage(String message) {
                            System.out.println("rx: " + message);
                            messageLatch.countDown();
                        }
                    });
                    session.getAsyncRemote().sendText(res.toJson());
                }
            }, cec, new URI("ws://localhost:8080/result"));
            messageLatch.await(100, TimeUnit.SECONDS);
        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void helloWorld() {
        messageLatch = new CountDownLatch(1);
        final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create
                ().build();
        ClientManager ws = ClientManager.createClient();
        try {
            ws.connectToServer(new Endpoint() {
                @Override
                public void onOpen(Session session, EndpointConfig config) {
                    try {
                        session.addMessageHandler(new MessageHandler.Whole<String>() {
                            @Override
                            public void onMessage(String message) {
                                System.out.println("rx: " + message);
                                messageLatch.countDown();
                            }
                        });
                        session.getBasicRemote().sendText(SENT_MESSAGE.toJson());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, cec, new URI("ws://localhost:8080/echo"));
            messageLatch.await(100, TimeUnit.SECONDS);
        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}