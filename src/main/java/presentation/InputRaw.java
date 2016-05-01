package presentation;

/**
 * InputRaw:
 */
public class InputRaw extends WSMessage {
    private final String MessageType = "InputRaw";

    @Override
    public String toJson() {
        return GSON.toJson(this,InputRaw.class);
    }
}
