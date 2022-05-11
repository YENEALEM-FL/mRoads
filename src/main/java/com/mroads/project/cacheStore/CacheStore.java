package com.mroads.project.cacheStore;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class CacheStore<T> {
    private final LoadingCache<String, T> cache;

    //Constructor to build Cache Store
    public CacheStore(int expiryDuration, TimeUnit timeUnit, T value) {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .build(new CacheLoader<String, T>() {
                    @Override
                    public T load(String key) throws Exception {
                        return add(key,value );
                    }
                });
    }

    //Method to fetch previously stored record using record key
    public T getEntry(String key) throws Exception{
        return cache.get(key);
    }

    //Method to put a new record in Cache Store with record key
    public T add(String key, T value) {
        if(key != null && value != null) {
            cache.put(key, value);
            System.out.println("Record stored in "
                    + value.getClass().getSimpleName()
                    + " Cache with Key = " + key);
        }
        return value;
    }
}