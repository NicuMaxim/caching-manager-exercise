package src.main.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.main.services.impl.CacheServiceImpl;
import src.main.data.Priority;
import src.main.services.impl.InvalidationServiceImpl;

public class PriorityServiceTest {

    @Test
    public void testRegister() throws InterruptedException {

        CacheServiceImpl cacheService = new CacheServiceImpl();
        InvalidationServiceImpl priorityService = new InvalidationServiceImpl();

        String object1 = "Object_1";
        cacheService.put("1", object1);
        priorityService.register("1", Priority.HIGH);

        Assertions.assertTrue(priorityService.check("1"));
        Assertions.assertNotNull(cacheService.get("1"));

        Thread.sleep(6000);

        Assertions.assertFalse(priorityService.check("1"));
        Assertions.assertNull(cacheService.get("1"));
    }

    @Test
    public void testUnregister() throws InterruptedException {

        CacheServiceImpl cacheService = new CacheServiceImpl();
        InvalidationServiceImpl priorityService = new InvalidationServiceImpl();

        String object1 = "Object_1";
        cacheService.put("1", object1);
        priorityService.register("1", Priority.HIGH);

        priorityService.unregister("1");
        Thread.sleep(6000);

        Assertions.assertFalse(priorityService.check("1"));
        Assertions.assertNotNull(cacheService.get("1"));
    }
}
