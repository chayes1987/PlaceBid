import org.jeromq.ZMQ;
import org.jeromq.ZMQ.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;

/*
    @author Conor Hayes
    The official documentation was consulted for the third party library 0mq used in this class
    0mq pub -> https://github.com/zeromq/jeromq/blob/master/src/test/java/guide/pathopub.java
    0mq sub -> https://github.com/zeromq/jeromq/blob/master/src/test/java/guide/pathosub.java
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
    private Context context = ZMQ.context();
    private static Socket publisher;

    @GET
    @Path("placebid/{id}/{email}")
    public boolean placeBid(@PathParam("id") String id, @PathParam("email") String email){
        if (publisher == null) {
            publisher = context.socket(ZMQ.PUB);
            publisher.bind(Constants.PUB_ADR);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String bidPlacedEvt = Constants.TOPIC + " <id>" + id + "</id> <params>" + email + "</params>";
        publisher.send(bidPlacedEvt.getBytes());
        return true;
    }

    @GET
    @Path("checkservice/")
    public boolean checkService(){ return true; }
}