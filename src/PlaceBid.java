import org.jeromq.ZMQ;
import org.jeromq.ZMQ.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;

@ApplicationPath("/bidder")
@Path("/services")
public class PlaceBid extends Application {
    private Context context = ZMQ.context();
    private Socket publisher = context.socket(ZMQ.PUB);

    public static void main(String[] args) {

    }

    @GET
    @Path("placebid/{id}/{email}")
    public boolean placeBid(@PathParam("id") String id, @PathParam("email") String email){
        return false;
    }
}
