package src.main;

public class CacheServiceImpl implements CacheServiceInterface {

    @Override
    public void put(String key, Object o) {

        if (key == null || key.isEmpty() || o == null) {
            System.out.println("Null keys and values are not allowed");
            return;
        }

        if (Cache.getCacheInstance().size() == 100) {
            System.out.println("Cache has already 100 elements and can't fit any more.");
            return;
        }

        Cache.getCacheInstance().put(key, o);
    }

    @Override
    public Object get(String key) {
        return Cache.getCacheInstance().get(key);
    }

    @Override
    public void remove(String key) {
        Cache.getCacheInstance().remove(key);
    }

    @Override
    public void invalidate() {
        Cache.getCacheInstance().clear();
    }
}
