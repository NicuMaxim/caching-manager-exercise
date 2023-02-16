package src.main.services.impl;

import src.main.data.Cache;
import src.main.data.Priority;
import src.main.services.InvalidationServiceInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class InvalidationServiceImpl implements InvalidationServiceInterface {

    private Map<String, Object> cache = Cache.getCacheInstance();
    private Map<String, ScheduledFuture<?>> expiries = new HashMap<>();
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void register(String key, Priority priority) {
        synchronized (cache) {

            ScheduledFuture<?> expiry = expiries.remove(key);
            if (expiry != null) {
                expiry.cancel(false);
            }

            expiry = executor.schedule(() -> {
                expiries.remove(key);
                cache.remove(key);
                System.out.println("Object: " + key + " was automatically removed by schedule after " + priority.expirationTime + " milliseconds.");
            }, priority.expirationTime, TimeUnit.MILLISECONDS);
            expiries.put(key, expiry);
        }
    }

    @Override
    public void unregister(String key) {
        synchronized (cache) {
            if (cache.get(key) == null) {
                System.out.println("Object" + key + " is missing.");
                return;
            }
            ScheduledFuture<?> expiry = expiries.remove(key);
            if (expiry != null) {
                expiry.cancel(false);
            }
            System.out.println("Object: " + key + " was unregistered.");
        }
    }

    @Override
    public boolean check(String key) {
        synchronized (cache) {
            if (cache.get(key) == null) {
                System.out.println("Object" + key + " is missing.");
                return false;
            }
            ScheduledFuture<?> expiry = expiries.get(key);
            if (expiry == null) {
                System.out.println("Object: " + key + " was unregistered.");
                return false;
            } else {
                System.out.println("Object: " + key + " is still valid.");
                return true;
            }
        }
    }

    @Override
    public void shutdown(){
        executor.shutdown();
    }
}
