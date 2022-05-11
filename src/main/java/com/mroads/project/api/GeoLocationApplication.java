package com.mroads.project.api;

import com.codahale.metrics.health.HealthCheck;
import com.mroads.project.config.GeoLocationConfiguration;
import com.mroads.project.dao.GeoLocationDAO;
import com.mroads.project.entity.GeoLocation;
import com.mroads.project.resources.GeoLocationResource;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class GeoLocationApplication extends Application<GeoLocationConfiguration> {

    public static void main(final String[] args) throws Exception {
        new GeoLocationApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropWizard-GeoLocation";
    }

    @Override
    public void run(final GeoLocationConfiguration configuration,
                    final Environment environment) {

        // TODO: implement application
        GeoLocationDAO geoLocationDAO = new GeoLocationDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new GeoLocationResource(new GeoLocationDAO(hibernateBundle.getSessionFactory())));
        environment.healthChecks().register("geoLocation", new HealthCheck() {
            @Override
            protected Result check() throws Exception {

                return Result.healthy();
            }
        });
    }

      HibernateBundle<GeoLocationConfiguration> hibernateBundle = new HibernateBundle<GeoLocationConfiguration>(GeoLocation.class) {

        @Override
          public PooledDataSourceFactory getDataSourceFactory(GeoLocationConfiguration geoLocationConfiguration) {
              return geoLocationConfiguration.getDataSourceFactory();
          }

      };

    @Override
    public void initialize(Bootstrap<GeoLocationConfiguration> bootstrap){
        bootstrap.addBundle(hibernateBundle);
    }

    }


