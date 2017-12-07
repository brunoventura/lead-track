package com.perone;

import com.sun.istack.internal.NotNull;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Test {

    @GET
    @Path("/{userName}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUser(@NotNull @PathParam("userName") String userName) {

        return Response.status(200).entity(userName).build();

    }

}
