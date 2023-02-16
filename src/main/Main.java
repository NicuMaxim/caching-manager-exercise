package src.main;

import src.main.data.Priority;
import src.main.services.impl.CacheServiceImpl;
import src.main.services.impl.InvalidationServiceImpl;

class Main {

    public static void main(String[] args) throws InterruptedException {

        String object1 = "object1";
        String object2 = "object2";
        String object3 = "object3";

        CacheServiceImpl cacheService = new CacheServiceImpl();
        InvalidationServiceImpl priorityService = new InvalidationServiceImpl();

        CachingManager cachingManager = new CachingManager(cacheService, priorityService);

        cachingManager.putAndRegister("1", object1, null);
        cachingManager.putAndRegister("2", object2, Priority.MEDIUM);
        cachingManager.putAndRegister("3", object3, Priority.LOW);

        cachingManager.unregister("1");

        cachingManager.get("2");

        Thread.sleep(20000);

        cachingManager.invalidateAll();
    }
}








