package com.mroads.project.dao;

import com.mroads.project.entity.GeoLocation;
import io.dropwizard.hibernate.AbstractDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

public class GeoLocationDAO extends AbstractDAO<GeoLocation> {
    public GeoLocationDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public GeoLocation findGeoLocationByAddress(String id){
        String GeoQuery = "from GeoLocation g where g.id = :id";
        Query query = currentSession().createQuery(GeoQuery);
        return (GeoLocation) query.getSingleResult();
    }

    public void saveGeolocation(GeoLocation geoLocation){

        if(StringUtils.isEmpty(geoLocation.getId()))
            geoLocation.setId(UUID.randomUUID().toString());
         persist(geoLocation);
    }


}
