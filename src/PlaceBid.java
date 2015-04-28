import broker.BrokerFacade;
import broker.IBroker;
import utils.Constants;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

/*
    The official documentation was consulted for the third party libraries
    Coding Standards -> http://www.oracle.com/technetwork/java/codeconvtoc-136057.html
    URL for testing web service on localhost from browser -> http://localhost:8080/placebidservice/bidder/services/checkservice
    URL for AWS Web Service -> http://54.171.120.118:8080/placebidservice/bidder/services/checkservice
    RESTful web service -> http://www.mkyong.com/webservices/jax-rs/jax-rs-pathparam-example/
 */

/**
 * @author Conor Hayes
 * Place Bid
 */
@ApplicationPath(Constants.APPLICATION_PATH)
@Path(Constants.PATH)
public class PlaceBid extends Application {
    private IBroker _broker;

    /**
     * Consumed by bidders to place a bid
     * @param id The ID of the auction
     * @param email The email of the bidder
     * @return The response code
     */
    @GET
    @Path("placebid/{id}/{email}")
    public Response placeBid(@PathParam("id") String id, @PathParam("email") String email){
        if(_broker == null) {
            _broker = BrokerFacade.getBroker();
        }
        String bidPlacedEvt = Constants.TOPIC + " <id>" + id + "</id> <params>" + email + "</params>";
        _broker.publishBidPlacedEvt(bidPlacedEvt);
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