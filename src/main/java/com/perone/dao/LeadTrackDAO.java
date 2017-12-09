package com.perone.dao;

import com.perone.entity.LeadTrack;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import java.util.List;

public class LeadTrackDAO extends AbstractDAO<LeadTrack, ObjectId> {

    public LeadTrackDAO() {
        super(LeadTrack.class);
    }

    public List<LeadTrack> findTracksByLeadId(ObjectId leadId) {
        Query<LeadTrack> q = createQuery();
        q.field("idLead").equal(leadId);
        return q.asList();
    }

}
