package com.perone.lead.resource;

import com.perone.entity.Lead;
import com.perone.entity.LeadTrack;
import com.perone.exception.BusinessException;
import com.perone.lead.bean.LeadBean;
import lombok.Data;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("lead")
public class LeadCatalogService {

    @Inject
    private LeadBean bean;

    @Path("{id}")
    public LeadService get(@NotNull @PathParam("id") String id) throws Exception {
        Lead result = this.bean.getLeadById(id);
        return new LeadService(result, this.bean);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Lead save(@NotNull ReceiveLeadDTO dto) throws BusinessException {
        return bean.leadInsert(dto);
    }

    @GET
    @Path("report")
    @Produces(MediaType.APPLICATION_JSON)
    public List<LeadReportDTO> getLeadReport() throws BusinessException {
        return bean.leadReport();
    }

    @Data
    public static class ReceiveLeadDTO {
        private String id;
        private String name;
        private String email;
    }

    @Data
    public static class LeadReportDTO {
        private Lead lead;
        private List<LeadTrack> tracks;
    }

}
