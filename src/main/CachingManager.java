package src.main;

import src.main.data.Priority;
import src.main.services.impl.CacheServiceImpl;
import src.main.services.impl.InvalidationServiceImpl;

public class CachingManager {

    CacheServiceImpl cacheService;
    InvalidationServiceImpl invalidationService;

    public CachingManager(CacheServiceImpl cacheService, InvalidationServiceImpl invalidationService) {
        this.cacheService = cacheService;
        this.invalidationService = invalidationService;
    }

    public void putAndRegister(String key, Object o, Priority priority) {
        cacheService.put(key, o);

        if (priority == null) priority = Priority.HIGH;
        invalidationService.register(key, priority);
    }

    public void unregister(String key) {
        invalidationService.unregister(key);
    }

    public Object get(String key) {
        return cacheService.get(key);
    }

    public void invalidateAll() {
        cacheService.invalidateAll();
        invalidationService.shutdown();
    }
}
