package presentation;

import com.google.gson.Gson;

/**
 * Result: Result of solver
 */
public interface Result {
    // toJson() return Stringified Json
    String toJson();
    // getMeta outputs meta information about the result
    String getMeta();
}
