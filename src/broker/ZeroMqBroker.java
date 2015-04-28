package broker;

import org.jeromq.ZMQ;
import utils.Constants;

/*
    The official documentation was consulted for the third party library 0mq used in this class
    0mq pub -> https://github.com/zeromq/jeromq/blob/master/src/test/java/guide/pathopub.java
    0mq sub -> https://github.com/zeromq/jeromq/blob/master/src/test/java/guide/pathosub.java
 */

/**
 * @author Conor Hayes
 * 0mq Broker
 */
public class ZeroMqBroker implements IBroker {
    private ZMQ.Context _context = ZMQ.context(1);
    private ZMQ.Socket _publisher = _context.socket(ZMQ.PUB);

    /**
     * Publish Bid Placed Event
     * @param message The message to send
     */
    @Override
    public void publishBidPlacedEvt(String message) {
        // Initialize the publisher, only done once
        if (_publisher == null) {
            _publisher = _context.socket(ZMQ.PUB);
            _publisher.bind(Constants.PUB_ADR);
            // Allow time for the publisher to bind to the address
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        _publisher.send(message.getBytes());
    }
}