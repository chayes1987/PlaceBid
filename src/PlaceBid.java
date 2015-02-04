import javax.ws.rs.*;
import javax.ws.rs.core.Application;

@ApplicationPath("/bidder")
@Path("/services")
public class PlaceBid extends Application {
    public static void main(String[] args) {

    }

    @GET
    @Path("placebid/{id}/{email}")
    public boolean placeBid(@PathParam("id") String id, @PathParam("email") String email){
        return false;
    }
}
