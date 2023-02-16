package src.main.services.impl;

import src.main.data.Cache;
import src.main.services.CacheServiceInterface;

import java.util.Map;

public class CacheServiceImpl implements CacheServiceInterface {

    Map<String, Object> cacheInstance = Cache.getCacheInstance();

    @Override
    public void put(String key, Object o) {

        if (key == null || key.isEmpty() || o == null) {
            System.out.println("Null keys and values are not allowed");
            return;
        }
        if (cacheInstance.size() == 100) {
            System.out.println("Cache has already 100 elements and can't fit any more.");
            return;
        }
        cacheInstance.put(key, o);
        System.out.println("New Object put with key: " + key);
    }

    @Override
    public Object get(String key) {
        return cacheInstance.get(key);
    }

    @Override
    public void remove(String key) {
        cacheInstance.remove(key);
        System.out.println("Object by key " + key + " was removed.");
    }

    @Override
    public void invalidateAll() {
        cacheInstance.clear();
        System.out.println("All cache was invalidated.");
    }
}
