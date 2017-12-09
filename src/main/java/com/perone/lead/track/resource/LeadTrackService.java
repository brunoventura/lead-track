package com.perone.lead.track.resource;

import com.perone.entity.LeadTrack;
import com.perone.lead.track.bean.LeadTrackBean;
import lombok.NoArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@NoArgsConstructor
public class LeadTrackService {

    private LeadTrackBean bean;

    private LeadTrack entity;

    public LeadTrackService(LeadTrack entity, LeadTrackBean bean) {
        this.bean = bean;
        this.entity = entity;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        return Response.status(Response.Status.OK).entity(this.entity).build();
    }

}