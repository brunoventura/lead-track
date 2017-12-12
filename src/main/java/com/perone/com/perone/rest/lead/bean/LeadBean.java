package com.perone.com.perone.rest.lead.bean;

import com.perone.com.perone.rest.lead.resource.LeadCatalogService.LeadReportDTO;
import com.perone.dao.LeadDAO;
import com.perone.dao.LeadTrackDAO;
import com.perone.entity.Lead;
import com.perone.entity.LeadTrack;
import com.perone.com.perone.rest.exception.BusinessException;
import org.bson.types.ObjectId;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
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
    public Lead getLeadById(ObjectId leadId) {
        // Find Lead, if exists
        Lead lead = leadDAO.get(leadId);

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
     * @param lead an ReceiveLeadDTO
     * @return Lead entity or null
     * @see Lead
     */
    public Lead leadInsert(Lead lead) throws BusinessException {
        // Lead exists validation by email
        if (leadDAO.findByEmail(lead.getEmail()) != null) {
            throw new BusinessException("Lead Email already exists.");
        }

        // Lead exists validation by idLead
        if (leadDAO.get(lead.getId()) != null) {
            throw new BusinessException("Lead Identification already exists.");
        }

        Lead leadNew = Lead.builder()
                .id(lead.getId())
                .name(lead.getName())
                .email(lead.getEmail())
                .build();

        // save new Lead
        leadDAO.save(leadNew);

        log.info(String.format("Track received and saved - ID:[%s] e NAME:[%s]", leadNew.getId(), leadNew.getName()));

        return leadNew;
    }

    /**
     * Method to report of Lead with Track
     *
     * @param leadId if exists it will be used, it's optional,
     *               else bring all leads and their tracks
     * @return LeadReportDTO dto for the report content
     * @see LeadReportDTO
     */
    public List<LeadReportDTO> leadReport(ObjectId leadId) throws BusinessException {
        // Create dto result
        List<LeadReportDTO> results = new ArrayList<>();

        // find all Leads and Tracks
        List<Lead> leads = new ArrayList<>();

        if (leadId != null) {
            leads.add(leadDAO.get(leadId));
        } else {
            leads.addAll(leadDAO.find().asList());
        }

        leads.forEach(lead -> {
            LeadReportDTO dto = new LeadReportDTO();
            dto.setLead(lead);
            dto.setTracks(new ArrayList<>());

            List<LeadTrack> tracks = leadTrackDAO.findTracksByLeadId(lead.getId());
            tracks.forEach(track -> {
                dto.getTracks().add(track.getUrl());
            });

            results.add(dto);
        });

        // Return DTO list
        return results;
    }

}
