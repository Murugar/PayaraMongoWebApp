package com.iqmsoft.control;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import static javax.ejb.LockType.READ;

@Singleton
@Startup
public class MongoProd {

    public MongoClient mongoClient;

    @PostConstruct
    public void init(){
        String mongoService = "db";
        Integer port = 27017;
        mongoClient = new MongoClient(mongoService, port);
    }


    @Lock(READ)
    public Datastore getDatastore() {
        final Morphia morphia = new Morphia();
        morphia.mapPackage("com.iqmsoft.entity");

        return morphia.createDatastore(mongoClient, "mini_app");
    }
}
