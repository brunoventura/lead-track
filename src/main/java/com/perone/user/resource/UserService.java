package com.perone.user.resource;

import com.perone.entity.User;
import com.perone.user.bean.UserBean;
import lombok.NoArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@NoArgsConstructor
public class UserService {

    private UserBean bean;

    private User entity;

    public UserService(User entity, UserBean bean) {
        this.bean = bean;
        this.entity = entity;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        return Response.status(Response.Status.OK).entity(this.entity).build();
    }

}