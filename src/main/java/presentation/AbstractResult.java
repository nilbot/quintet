package presentation;

/**
 * AbstractResult:
 */
public abstract class AbstractResult extends GsonSerialzable {
    protected String solvingStrategy;
    protected final String MessageType = "Result";
}
