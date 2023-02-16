package src.main;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private static Map<String, Object> cacheInstance;

    private Cache(){}

    public static Map<String, Object> getCacheInstance() {

        if (cacheInstance == null) {
            cacheInstance = new HashMap<>();
        }
        return cacheInstance;
    }
}
