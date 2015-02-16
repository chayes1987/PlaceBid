import org.jeromq.ZMQ;
import org.jeromq.ZMQ.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;

/*
    @author Conor Hayes
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
    private Socket publisher = context.socket(ZMQ.PUB);

    public static void main(String[] args) { new PlaceBid().subscribe(); }

    private void subscribe(){
        publisher.bind(Constants.PUB_ADR);
        new Thread(() -> {
            Socket subscriber = context.socket(ZMQ.SUB);
            subscriber.connect(Constants.ACK_ADR);
            subscriber.subscribe(Constants.BID_PLACED_ACK_TOPIC.getBytes());

            while (true) {
                System.out.println(new String(subscriber.recv()));
            }
        }).start();
    }

    @GET
    @Path("placebid/{id}/{email}")
    public boolean placeBid(@PathParam("id") String id, @PathParam("email") String email){
        String bidPlacedEvt = "BidPlaced <id>" + id + "</id> <params>" + email + "</params>";
        publisher.send(bidPlacedEvt.getBytes());
        return true;
    }
}