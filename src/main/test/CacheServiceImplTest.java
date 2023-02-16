package src.main.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.main.CacheServiceImpl;

public class CacheServiceImplTest {

    @Test
    public void testPutAndGet() {

        CacheServiceImpl cacheService = new CacheServiceImpl();
        String object1 = "Object_1";
        cacheService.put("1", object1);

        Assertions.assertEquals(object1, cacheService.get("1"));
    }

    @Test
    public void testCacheMiss() {

        CacheServiceImpl cacheService = new CacheServiceImpl();
        String object1 = "Object_1";

        cacheService.put("", object1);
        Assertions.assertNull(cacheService.get(""));

        cacheService.put(null, object1);
        Assertions.assertNull(cacheService.get(null));

        cacheService.put("1", null);
        Assertions.assertNull(cacheService.get("1"));
    }

    @Test
    public void testCacheEviction() {
        CacheServiceImpl cacheService = new CacheServiceImpl();
        cacheService.put("1", "Object_1");
        cacheService.put("2", "Object_2");
        cacheService.put("3", "Object_3");

        cacheService.invalidate();

        Assertions.assertNull(cacheService.get("1"));
        Assertions.assertNull(cacheService.get("2"));
        Assertions.assertNull(cacheService.get("3"));
    }

    @Test
    public void testCacheSizeLimit() {
        CacheServiceImpl cacheService = new CacheServiceImpl();

        for (int i = 1; i <=100; i++) {
            cacheService.put(String.valueOf(i), "Object_" + i);
        }

        cacheService.put("101", "Object_101");
        Assertions.assertNull(cacheService.get("101"));
    }
}
