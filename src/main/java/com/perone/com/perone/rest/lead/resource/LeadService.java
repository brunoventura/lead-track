package com.perone.com.perone.rest.lead.resource;

import com.perone.entity.Lead;
import com.perone.com.perone.rest.lead.bean.LeadBean;
import lombok.NoArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@NoArgsConstructor
public class LeadService {

    private LeadBean bean;

    private Lead entity;

    public LeadService(Lead entity, LeadBean bean) {
        this.bean = bean;
        this.entity = entity;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        return Response.status(Response.Status.OK).entity(this.entity).build();
    }

}