package src.main;

class Main {

    public static void main(String[] args) {

        String object1 = "object1";
        String object2 = "object2";
        String object3 = "object3";

        CacheServiceImpl cacheService = new CacheServiceImpl();

        System.out.println("Put 3 objects into cache: " + object1 + ", " + object2 + ", and " + object3);
        cacheService.put("1", object1);
        cacheService.put("2", object2);
        cacheService.put("3", object3);

        System.out.println("Get object by key 1: " + cacheService.get("1") + "\n");

        System.out.println("Delete object by key 2.");
        cacheService.remove("2");
        System.out.println("Check if object by key 2 exists: " + cacheService.get("2") + "\n");

        System.out.println("Clear all cache.");
        cacheService.invalidate();

        System.out.println("Check if any object remains in cache: " + cacheService.get("1") + cacheService.get("2") + cacheService.get("3"));

        System.out.println("\n------------");

        System.out.println("Check if we can put null object or null key:");
        cacheService.put("4", null);

    }
}








