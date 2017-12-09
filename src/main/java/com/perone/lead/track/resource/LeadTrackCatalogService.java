package com.perone.lead.track.resource;

import com.perone.entity.LeadTrack;
import com.perone.lead.track.bean.LeadTrackBean;
import lombok.Data;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("leadtrack")
public class LeadTrackCatalogService {

    @Inject
    private LeadTrackBean bean;

    @Path("{id}")
    public LeadTrackService get(@NotNull @PathParam("id") String id) throws Exception {
        LeadTrack result = this.bean.getLeadTrackById(id);
        return new LeadTrackService(result, this.bean);
    }

    @POST
    @Path("insert")
    @Consumes(MediaType.APPLICATION_JSON)
    public void insert(@NotNull ReceiveLeadTrackDTO dto) {
        bean.leadTrackInsert(dto);
    }

    @Data
    public static class ReceiveLeadTrackDTO {
        private String idLead;
        private String url;
    }

}
