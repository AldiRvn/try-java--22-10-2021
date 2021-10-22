public interface Service {
    int MB_MAX_MEMORY_LIMIT = 4024;
    int MB_MIN_MEMORY_LIMIT = 512;

    void build();
    void start();
    void showInfo();
    void stop();
}
