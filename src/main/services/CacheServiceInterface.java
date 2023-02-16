package src.main.services;

public interface CacheServiceInterface {

    void put(String key, Object o);

    Object get(String key);

    void remove(String key);

    void invalidateAll();


}
