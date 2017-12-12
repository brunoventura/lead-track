package com.perone.com.perone.rest.lead.resource;

import com.perone.com.perone.rest.lead.bean.LeadBean;
import com.perone.entity.Lead;
import com.perone.com.perone.rest.exception.BusinessException;
import lombok.Data;
import org.bson.types.ObjectId;

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
    public LeadService get(@NotNull @PathParam("id") ObjectId id) throws Exception {
        Lead result = this.bean.getLeadById(id);
        return new LeadService(result, this.bean);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Lead save(@NotNull Lead lead) throws BusinessException {
        return bean.leadInsert(lead);
    }

    @GET
    @Path("report/{leadId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<LeadReportDTO> getLeadReport(@PathParam("leadId") ObjectId leadId) throws BusinessException {
        return bean.leadReport(leadId);
    }

    @Data
    public static class LeadReportDTO {
        private Lead lead;
        private List<String> tracks;
    }

}
