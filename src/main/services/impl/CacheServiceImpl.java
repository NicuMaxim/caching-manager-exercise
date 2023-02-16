package src.main.services.impl;

import src.main.data.Cache;
import src.main.services.CacheServiceInterface;

import java.util.Map;

public class CacheServiceImpl implements CacheServiceInterface {

    Map<String, Object> cache = Cache.getCacheInstance();

    @Override
    public void put(String key, Object o) {
        synchronized (cache) {
            if (key == null || key.isEmpty() || o == null) {
                System.out.println("Null keys and values are not allowed");
                return;
            }
            if (cache.size() == 100) {
                System.out.println("Cache has already 100 elements and can't fit any more.");
                return;
            }
            if (cache.get(key) != null) {
                System.out.println("An unique key is required.");
                return;
            }

            cache.put(key, o);
            System.out.println("New Object added with key: " + key);
        }
    }

    @Override
    public Object get(String key) {
        synchronized (cache) {
            return cache.get(key);
        }
    }

    @Override
    public void remove(String key) {
        synchronized (cache) {
            cache.remove(key);
            System.out.println("Object: " + key + " was removed.");
        }
    }

    @Override
    public void invalidateAll() {
        synchronized (cache) {
            cache.clear();
            System.out.println("All cache was evicted.");
        }
    }
}
