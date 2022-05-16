package com.mroads.project.test;

import com.mroads.project.core.Geolocation;
import com.mroads.project.dao.GeolocationDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


public class TestApplication {
    private static final GeolocationDAO geolocationDAO = mock(GeolocationDAO.class);
    Geolocation geolocation= new Geolocation();
    @BeforeEach
    void setUp() {

        geolocation.setIpAddress("24.48.0.1");
        geolocation.setCity("Dallas");
        geolocation.setCountryCode("001");
        geolocation.setAsNumber("75044");
        geolocation.setLongitude(23434.02);
        geolocation.setLatitude(32123.02);
        geolocation.setiSPName("ETC");
        geolocation.setOrganization("Smart Telecom");

    }

    @AfterEach
    void tearDown() {
        reset(geolocationDAO);
    }
//Testing to create a new geolocation
    @Test
    void createGeolocation() {
        when(geolocationDAO.createGeolocation(geolocation)).thenReturn(geolocation);
    }
//Testing to search a geolocation by IP Address
    @Test
    void findGeolocation() {
        Assertions.assertEquals(geolocation.getIpAddress(),"24.48.0.1");
    }

}
