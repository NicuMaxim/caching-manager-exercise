package src.main.services;

import src.main.data.Priority;

public interface InvalidationServiceInterface {

    void register(String key, Priority priority);

    void unregister(String key);

    boolean check(String key);

    void shutdown();
}
