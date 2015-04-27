import org.jeromq.ZMQ;
import org.jeromq.ZMQ.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * @author Conor Hayes
 */

/*
    The official documentation was consulted for the third party library 0mq used in this class
    0mq pub -> https://github.com/zeromq/jeromq/blob/master/src/test/java/guide/pathopub.java
    Config -> http://www.mkyong.com/java/java-properties-file-examples/
    Coding Standards -> http://www.oracle.com/technetwork/java/codeconvtoc-136057.html
    URL for testing web service on localhost from browser -> http://localhost:8080/placebidservice/bidder/services/checkservice
    URL for AWS Web Service -> http://54.171.120.118:8080/placebidservice/bidder/services/checkservice
    RESTful web service -> http://www.mkyong.com/webservices/jax-rs/jax-rs-pathparam-example/
 */

/**
 * This class is a RESTful web service which handles user/bidder requests
 */
@ApplicationPath(Constants.APPLICATION_PATH)
@Path(Constants.PATH)
public class PlaceBid extends Application {
    private Context _context = ZMQ.context();
    private static Socket _publisher;

    /**
     * Consumed by bidders to place a bid
     * @param id The ID of the auction
     * @param email The email of the bidder
     * @return The response code
     */
    @GET
    @Path("placebid/{id}/{email}")
    public Response placeBid(@PathParam("id") String id, @PathParam("email") String email){
        // Initialize the publisher, only done once
        if (_publisher == null) {
            _publisher = _context.socket(ZMQ.PUB);
            _publisher.bind(Constants.PUB_ADR);
            // Allow time for the publisher to bind to the address
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
        // Build and publish the BidPlaced event
        String bidPlacedEvt = Constants.TOPIC + " <id>" + id + "</id> <params>" + email + "</params>";
        _publisher.send(bidPlacedEvt.getBytes());
        return Response.status(Response.Status.OK).build();
    }

    /**
     * Consumed by CheckServiceHealth service for monitoring purposes
     * @return The response code
     */
    @GET
    @Path("checkservice/")
    public Response checkService(){ return Response.status(200).build(); }
}