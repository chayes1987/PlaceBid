package broker;

/**
 * @author Conor Hayes
 * IBroker interface
 */
public interface IBroker {

    /**
     * Publish Bid Placed Event
     * @param message The message to send
     */
    void publishBidPlacedEvt(String message);
}
