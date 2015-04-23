import org.jeromq.ZMQ;
import org.jeromq.ZMQ.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

/*
    @author Conor Hayes
    The official documentation was consulted for the third party library 0mq used in this class
    0mq pub -> https://github.com/zeromq/jeromq/blob/master/src/test/java/guide/pathopub.java
    Config -> http://www.mkyong.com/java/java-properties-file-examples/
    URL for testing web service on localhost from browser:
        http://localhost:8080/placebidservice/bidder/services/placebid/1/ch1987@live.ie
    URL for AWS Web Service:
        http://54.171.120.118:8080/placebidservice/bidder/services/placebid/1/ch1987@live.ie
    Information researched for RESTful web service:
        http://www.mkyong.com/webservices/jax-rs/jax-rs-pathparam-example/
 */

@ApplicationPath(Constants.APPLICATION_PATH)
@Path(Constants.PATH)
public class PlaceBid extends Application {
    private Context _context = ZMQ.context();
    private static Socket _publisher;

    @GET
    @Path("placebid/{id}/{email}")
    public Response placeBid(@PathParam("id") String id, @PathParam("email") String email){
        if (_publisher == null) {
            _publisher = _context.socket(ZMQ.PUB);
            _publisher.bind(Constants.PUB_ADR);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return Response.status(501).build();
            }
        }
        String bidPlacedEvt = Constants.TOPIC + " <id>" + id + "</id> <params>" + email + "</params>";
        _publisher.send(bidPlacedEvt.getBytes());
        return Response.status(200).build();
    }

    @GET
    @Path("checkservice/")
    public Response checkService(){ return Response.status(200).build(); }
}