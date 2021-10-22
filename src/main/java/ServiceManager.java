public class ServiceManager {
    private final Service service;

    public ServiceManager(Service service) {
        this.service = service;
    }

    void build() {
        this.service.build();
    }

    void start() {
        this.service.start();
    }

    void stop() {
        this.service.stop();
    }
}
