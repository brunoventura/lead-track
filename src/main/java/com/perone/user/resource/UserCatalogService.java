package com.perone.user.resource;

import com.perone.entity.User;
import com.perone.user.bean.UserBean;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("user")
public class UserCatalogService {

    @EJB
    private UserBean bean;

    @Path("{id}")
    public UserService get(@PathParam("id") Long id) throws Exception {
        User result = this.bean.getUserById(id);
        return new UserService(result, this.bean);
    }

}
