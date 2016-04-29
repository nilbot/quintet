package presentation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * GsonSerialzable:
 */
public abstract class GsonSerialzable {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting()
            .create();
}
