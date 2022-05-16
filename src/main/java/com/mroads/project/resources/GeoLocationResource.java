package com.mroads.project.resources;

import com.codahale.metrics.annotation.Timed;
import com.mroads.project.core.Geolocation;
import com.mroads.project.dao.GeolocationDAO;
import com.mroads.project.exception.GeolocationDataNotFoundException;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.caching.CacheControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Path("/geolocation")
@Produces(MediaType.APPLICATION_JSON)

public class GeolocationResource {
    private final GeolocationDAO geolocationDAO;
    private Client client;
    private static Logger log = LoggerFactory.getLogger(Geolocation.class);
    private final String getURL = "http://ip-api.com/json/";


    public GeolocationResource( GeolocationDAO geolocationDAO) {
        this.geolocationDAO = geolocationDAO;

    }


    @POST
    @Path("/add")
    @UnitOfWork
    public void addGeolocation(@Valid Geolocation geolocation ){
        log.info("insert new geolocation data to the database.");
        geolocationDAO.createGeolocation(geolocation);

    }
    @GET
    @Timed
    @Path("/find/{ipAddress}")
    @UnitOfWork
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.MINUTES)
    public Geolocation getGeoLocationByAddress(@Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}" +
            "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])$", message = "IP address must be in the format: 0.0.0.0 to 255.255.255.255") @PathParam("ipAddress") String ipAddress) throws GeolocationDataNotFoundException {

            Geolocation geolocation = geolocationDAO.findGeoLocationByAddress(ipAddress);

            if(geolocation!=null) {
                if (!LocalDateTime.now().minusMinutes(5L).isAfter(geolocation.getUpdateTime()))
                    log.info("Data is fetched from cache.");
                else
                    log.info("Data is fetched from database.");

                return geolocation;
            }

            try {
                log.info("External API is called to search IP address: " + ipAddress);
                geolocation = client.target(getURL + ipAddress).request().get().readEntity(Geolocation.class);

                if (geolocation != null) {
                    log.info("Getting geolocation " + ipAddress + " from the API.");
                    return geolocation;
                }
            }
            catch (Exception e){
                log.info("No geolocation with " +ipAddress + " is found.");
                throw new WebApplicationException(e.getMessage() + Response.Status.NOT_FOUND);
            }
        return null;
    }
    @GET
    @Path("/getAll")
    @UnitOfWork
    public List<Geolocation> getGeolocations(){
        log.info("Get all geolocation data from database.");
        return geolocationDAO.findAll();

    }

}
