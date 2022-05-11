package com.mroads.project.service;

import com.mroads.project.cacheStore.CacheStore;
import com.mroads.project.dao.GeoLocationDAO;
import com.mroads.project.entity.GeoLocation;
import com.mroads.project.exception.GeoLocationDataNotFoundException;
import com.mroads.project.resources.GeoLocationResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class GeoLocationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeoLocationService.class);
    private  GeoLocationResource geoLocationResource;
    private  Client client;
    CacheStore<GeoLocation> cacheStore;
    private GeoLocationDAO geoLocationDAO;

    public GeoLocationService(GeoLocationResource geoLocationResource, Client client, CacheStore<GeoLocation> cacheStore, GeoLocationDAO geoLocationDAO) {
        this.geoLocationResource = geoLocationResource;
        this.client = client;
        this.cacheStore = cacheStore;
        this.geoLocationDAO = geoLocationDAO;
    }

    public GeoLocationService() {
    }

    public GeoLocation getGeoLocationByAddress(String ipAddress) throws Exception {
        GeoLocation geoLocation;
        geoLocation = cacheStore.getEntry(ipAddress);
        if(geoLocation == null) {
            LOGGER.info("not in cache " + ipAddress);
            try {
                geoLocation = geoLocationDAO.findGeoLocationByAddress(ipAddress);
            }catch (NoResultException e){
                LOGGER.info("No data Found On for "+ipAddress+" "+e.getLocalizedMessage());
            }
            if(geoLocation==null) {
                LOGGER.info("not in db " + ipAddress);
                try {
                    WebTarget webTarget = client.target("https://ip-api.com/json/" + ipAddress);
                    Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
                    Response response = invocationBuilder.get();
                    geoLocation = response.readEntity(GeoLocation.class);
                }catch (Exception e){
                    throw new GeoLocationDataNotFoundException("No Data Found for this IP:"+ipAddress);
                }
                LOGGER.info("from api call "+ipAddress);
                geoLocationResource.addGeoLocation(geoLocation);
            }
            cacheStore.add(ipAddress, geoLocation);
        }
        return geoLocation;
    }
}