package com.perone.dao;

import com.perone.config.MongoConn;
import org.mongodb.morphia.dao.BasicDAO;

public abstract class AbstractDAO<T, K> extends BasicDAO<T, K> {

    public AbstractDAO(Class<T> entityClass) {
        super(entityClass, MongoConn.getDatastore());
    }

}
