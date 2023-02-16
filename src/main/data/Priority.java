package src.main.data;

public enum Priority {
    LOW(15000L), MEDIUM(10000L), HIGH(5000L);

    public final long expirationTime;

    Priority(long expirationTime) {
        this.expirationTime = expirationTime;
    }
}
