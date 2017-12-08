package com.perone.config;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.reflections.Reflections;

import java.util.Arrays;

public class MongoConn {

    private final static String MONGO_DATABASE_NAME = "lead-track";

    private final static String MONGO_ENTITY_PACKAGE = "com.decora.mongo.entity";

    private static MongoConn singleton;

    private final MongoClient mongoClient;

    private final Morphia morphia;

    private final Datastore datastore;

    private final MongoDatabase database;

    private MongoConn() {
        MongoClientURI uri  = new MongoClientURI("mongodb://perone:perone@ds133856.mlab.com:33856/lead-track");

        this.mongoClient = new MongoClient(uri);
        this.database = this.mongoClient.getDatabase(MONGO_DATABASE_NAME);
        this.morphia = new Morphia();
        this.morphia.mapPackage(MONGO_ENTITY_PACKAGE);

        this.datastore = morphia.createDatastore(mongoClient, MONGO_DATABASE_NAME);
        this.datastore.ensureIndexes(true);

        Reflections reflections = new Reflections(MONGO_ENTITY_PACKAGE);
        for (Class<?> aClass : reflections.getTypesAnnotatedWith(Entity.class)) {
            this.morphia.map(aClass);
        }
    }

    public static MongoClient getMongoClient() {
        return getInstance().mongoClient;
    }

    public static MongoDatabase getDatabase() {
        return MongoConn.getInstance().database;
    }

    public static Morphia getMorphia() {
        return getInstance().morphia;
    }

    public static Datastore getDatastore() {
        return getInstance().datastore;
    }

    private static MongoConn getInstance() {
        if (singleton == null) {
            synchronized (MongoConn.class) {
                if (singleton == null) {
                    singleton = new MongoConn();
                }
            }
        }
        return singleton;
    }
}
