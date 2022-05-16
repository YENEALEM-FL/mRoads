package com.mroads.project.api;

import com.codahale.metrics.health.HealthCheck;
import com.mroads.project.cacheStore.CacheStore;
import com.mroads.project.config.GeolocationConfiguration;
import com.mroads.project.core.Geolocation;
import com.mroads.project.dao.GeolocationDAO;
import com.mroads.project.exception.GeolocationDataNotFoundException;
import com.mroads.project.resources.GeolocationResource;

import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class GeolocationApplication extends Application<GeolocationConfiguration> {

    public static void main(final String[] args) throws Exception {
        new GeolocationApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropWizard-GeoLocation";
    }

    @Override
    public void run(final GeolocationConfiguration configuration,
                    final Environment environment) {
        final CacheStore cacheStore = CacheStore.getInstance();
        GeolocationDAO geolocationDAO = new GeolocationDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new GeolocationResource(geolocationDAO));

        cacheStore.initGeoCache(geolocationDAO);
        final GeolocationResource resource = new GeolocationResource(geolocationDAO);
        environment.jersey().register(resource);

        environment.healthChecks().register("geoLocation", new HealthCheck() {
            @Override
            protected Result check() throws Exception {
                try {
                    return Result.healthy();
                }
                catch (Exception e){
                    throw new GeolocationDataNotFoundException("");
                }
            }
        });
    }

      HibernateBundle<GeolocationConfiguration> hibernateBundle = new HibernateBundle<GeolocationConfiguration>(Geolocation.class) {

        @Override
          public PooledDataSourceFactory getDataSourceFactory(GeolocationConfiguration geoLocationConfiguration) {
              return geoLocationConfiguration.getDataSourceFactory();
          }

      };

    @Override
    public void initialize(Bootstrap<GeolocationConfiguration> bootstrap){
        bootstrap.addBundle(hibernateBundle);
    }

    }


