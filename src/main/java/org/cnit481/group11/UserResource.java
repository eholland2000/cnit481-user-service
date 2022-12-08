package org.cnit481.group11;

import org.cnit481.group11.connections.StockDataServiceConnection;
import org.cnit481.group11.models.User;
import org.cnit481.group11.models.UserLogin;
import org.cnit481.group11.models.UserStock;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.Instant;
import java.util.Optional;

@Path("/")
public class UserResource {

    @Inject
    StockDataServiceConnection stockDataService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello")
    public String hello() {
        return "hello!";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public String login(UserLogin login) {
        Optional<User> user = User.findByIdOptional(login.getUsername());
        if (user.isPresent()) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addStock/{username}")
    public String addStock(@PathParam("username") String username, @QueryParam("symbol") String symbol) {
        if (username != null && symbol != null && !username.equals("") && !symbol.equals("")) {
            UserStock.persist(new UserStock(username, symbol, Instant.now().toString()));
            if (stockDataService.triggerDataLoad(symbol)) {
                return "Success";
            } else {
                return "Error";
            }
        } else {
            return "Missing parameters";
        }
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/health")
    public String getHealth() {
        if (stockDataService.connected()) {
            return "health: good";
        } else {
            return "health: not connected to stockDataService";
        }
    }
}