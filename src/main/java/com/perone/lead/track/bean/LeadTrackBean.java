package com.perone.lead.track.bean;

import com.perone.dao.LeadDAO;
import com.perone.dao.LeadTrackDAO;
import com.perone.entity.LeadTrack;
import com.perone.lead.track.resource.LeadTrackCatalogService;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import java.util.logging.Logger;

public class LeadTrackBean {

    private Logger log = Logger.getLogger(this.getClass().getName());

    @Inject
    private LeadDAO leadDAO;

    @Inject
    private LeadTrackDAO leadTrackDAO;

    /**
     * Method to find any LeadTrack inserted on the database
     *
     * @param leadTrackId an identification of LeadTrack entity
     * @return the LeadTrack entity or null
     * @see LeadTrack
     */
    public LeadTrack getLeadTrackById(String leadTrackId) {
        // Find LeadTrack, if exists
        LeadTrack leadTrack = leadTrackDAO.get(new ObjectId(leadTrackId));

        log.info(String.format(
                "Find Lead Track - %s - ID:[%s].",
                (leadTrack != null ? "Found" : "Not Found"),
                leadTrackId)
        );

        // return LeadTrack founded
        return leadTrack;
    }

    /**
     * Method to insert on LeadTrack, called by JavaScript Lib
     * who was installed on client site
     *
     * @param dto an ReceiveLeadTrackDTO {@link LeadTrackCatalogService.ReceiveLeadTrackDTO}
     * @return the LeadTrack entity or null
     * @see LeadTrack
     */
    public void leadTrackInsert(LeadTrackCatalogService.ReceiveLeadTrackDTO dto) {
        // create a new LeadTrack registry
        LeadTrack leadTrack = LeadTrack.builder()
                .id(new ObjectId())
                .idLead(new ObjectId(dto.getIdLead()))
                .url(dto.getUrl())
                .build();

        // save the new registry
        leadTrackDAO.save(leadTrack);

        log.info(String.format("Track received and saved - ID:[%s] e URL:[%s]", dto.getIdLead(), dto.getUrl()));
    }

}
