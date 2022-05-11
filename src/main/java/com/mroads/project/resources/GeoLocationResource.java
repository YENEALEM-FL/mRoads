package com.mroads.project.resources;

import com.mroads.project.dao.GeoLocationDAO;
import com.mroads.project.entity.GeoLocation;
import com.mroads.project.service.GeoLocationService;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/geolocation")
@Produces(MediaType.APPLICATION_JSON)

public class GeoLocationResource {
  final GeoLocationDAO geoLocationDAO;

    public GeoLocationResource(GeoLocationDAO geoLocationDAO) {
        this.geoLocationDAO = geoLocationDAO;
    }

    @POST
    @Path("/add")
    @UnitOfWork
    public void addGeoLocation(@Valid GeoLocation geoLocation){

      geoLocationDAO.saveGeolocation(geoLocation);

    }
    @GET
    @Path("/find/{ipAddress}")
    @UnitOfWork
    public GeoLocation getGeoLocationByAddress(@PathParam("ipAddress") String ipAddress) throws Exception {

      GeoLocationService geoLocationService = new GeoLocationService();
      try {
        return geoLocationService.getGeoLocationByAddress(ipAddress);
      }
      catch(Exception e){
         e.printStackTrace();
      }
       return null;
    }



}
