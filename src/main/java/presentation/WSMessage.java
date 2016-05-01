package presentation;

import presentation.GsonSerialzable;

/**
 * WSMessage:
 */
public abstract class WSMessage extends GsonSerialzable{
    protected String MessageType;
    public abstract String toJson();
}
