package com.perone.lead.bean;

import com.perone.dao.LeadDAO;
import com.perone.dao.LeadTrackDAO;
import com.perone.entity.Lead;
import com.perone.exception.BusinessException;
import com.perone.lead.resource.LeadCatalogService.LeadReportDTO;
import com.perone.lead.resource.LeadCatalogService.ReceiveLeadDTO;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LeadBean {

    private Logger log = Logger.getLogger(this.getClass().getName());

    @Inject
    private LeadDAO leadDAO;

    @Inject
    private LeadTrackDAO leadTrackDAO;

    /**
     * Method to find any Lead inserted on the database
     *
     * @param leadId an identification of Lead entity
     * @return the Lead entity or null
     * @see Lead
     */
    public Lead getLeadById(String leadId) {
        // Find Lead, if exists
        Lead lead = leadDAO.get(new ObjectId(leadId));

        log.info(String.format(
                "Find Lead - %s - ID:[%s].",
                (lead != null ? "Found" : "Not Found"),
                leadId)
        );

        // return Lead founded
        return lead;
    }

    /**
     * Method to insert on Lead, called by JavaScript Lib
     * who was installed on client site
     *
     * @param dto an ReceiveLeadDTO
     * @return the Lead entity or null
     * @see Lead
     */
    public Lead leadInsert(ReceiveLeadDTO dto) throws BusinessException {
        // Lead exists validation by email
        if (leadDAO.findByEmail(dto.getEmail()) != null) {
            throw new BusinessException("Lead Email already exists.");
        }

        // Lead exists validation by idLead
        if (leadDAO.get(new ObjectId(dto.getId())) != null) {
            throw new BusinessException("Lead Identification already exists.");
        }

        Lead lead = Lead.builder()
                .id(new ObjectId(dto.getId()))
                .name(dto.getName())
                .email(dto.getEmail())
                .build();

        // save new Lead
        leadDAO.save(lead);

        log.info(String.format("Track received and saved - ID:[%s] e NAME:[%s]", lead.getId(), lead.getName()));

        return lead;
    }

    /**
     * Method to report of Lead with Track
     *
     * @return LeadReportDTO
     * @see LeadReportDTO
     */
    public List<LeadReportDTO> leadReport() throws BusinessException {
        // Create dto result
        List<LeadReportDTO> results = new ArrayList<>();

        // find all Leads and Tracks
        List<Lead> leads = leadDAO.find().asList();
        leads.forEach(lead -> {
            LeadReportDTO dto = new LeadReportDTO();
            dto.setLead(lead);
            dto.setTracks(leadTrackDAO.findTracksByLeadId(lead.getId()));
            results.add(dto);
        });

        // Return DTO list
        return results;
    }

}
