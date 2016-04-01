package presentation;

import com.google.gson.Gson;

/**
 * Result: Result of solver
 */
public interface Result {
    // JSON() returns a Gson Object
    Gson JSON();
    // String() return old school serialization result
    String String();
}
