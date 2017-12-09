package com.perone.dao;

import com.perone.entity.Lead;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;


public class LeadDAO extends AbstractDAO<Lead, ObjectId> {

    public LeadDAO() {
        super(Lead.class);
    }

    public Lead findByEmail(String email) {
        Query<Lead> q = createQuery();
        q.field("email").equal(email);
        return q.get();
    }

}
