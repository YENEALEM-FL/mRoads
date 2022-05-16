package com.mroads.project.cacheStore;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.mroads.project.core.Geolocation;
import com.mroads.project.dao.GeolocationDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheStore{
    private static final Logger logger = LoggerFactory.getLogger(CacheStore.class);
    private static final CacheStore cacheStore = new CacheStore();

    public static CacheStore getInstance() {
        return cacheStore;
    }

    private static LoadingCache<String, Geolocation> geoCache;

    public void initGeoCache(GeolocationDAO geolocationDAO) {
        if (geoCache == null) {
            geoCache = CacheBuilder.newBuilder()
                    .concurrencyLevel(10)
                    .maximumSize(200) // Maximum of 200 records can be cached
                    .expireAfterAccess(1, TimeUnit.MINUTES) // Cache will expire after 30 minutes
                    .recordStats()
                    .build(new CacheLoader<String, Geolocation>() { // Build the CacheLoader
                        @Override
                        public Geolocation load(String ipAddress) throws Exception {
                            logger.info("Fetching Geolocation Data from Cache");
                            return geolocationDAO.findGeoLocationByAddress(ipAddress);
                        }
                    });
        }
    }

    public Geolocation getGeolocationDataFromCache(String key) {
        try {
            CacheStats cacheStats = geoCache.stats();
            logger.info("CacheStats = {} ", cacheStats);
            return geoCache.get(key);
        } catch (ExecutionException e) {
            logger.error("Error Retrieving Elements from the Geolocation Cache"
                    + e.getMessage());
        }
        return null;
    }
}