import org.jeromq.ZMQ;
import org.jeromq.ZMQ.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;

@ApplicationPath("/bidder")
@Path("/services")
public class PlaceBid extends Application {
    private Context context = ZMQ.context();
    private Socket publisher = context.socket(ZMQ.PUB);
    private final String PUB_ADR = "tcp://127.0.0.1:1101", ACK_ADR = "tcp://127.0.0.1:1110";

    public static void main(String[] args) { new PlaceBid().subscribe(); }

    private void subscribe(){
        publisher.bind(PUB_ADR);
        // TODO Implement subscriber
    }

    @GET
    @Path("placebid/{id}/{email}")
    public boolean placeBid(@PathParam("id") String id, @PathParam("email") String email){
        // TODO Implement method
        return false;
    }
}
