package com.mroads.project.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Objects;

public class GeolocationConfiguration extends Configuration {
    @NotNull
    @Valid
    private DataSourceFactory dataSourceFactory;

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory(){
        if(Objects.isNull(dataSourceFactory)){
            return new DataSourceFactory();
        }
        return  this.dataSourceFactory;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory){
            this.dataSourceFactory =dataSourceFactory;
    }

}
