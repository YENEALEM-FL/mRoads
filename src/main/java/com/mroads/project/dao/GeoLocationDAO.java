package com.mroads.project.dao;


import com.mroads.project.core.Geolocation;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


public class GeolocationDAO extends AbstractDAO<Geolocation> {


    public GeolocationDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Geolocation findGeoLocationByAddress(String id){

        return get(id);
    }

    public Geolocation createGeolocation(Geolocation geolocation){

         return persist(geolocation);
    }
    public List<Geolocation> findAll() {
        return list(namedTypedQuery("org.example.core.Geolocation.findAll"));
    }

}
