import java.util.Scanner;

public class Management implements Service {
    private String name;
    private static final int DEFAULT_PORT = 8080;
    private boolean Operating = true;

    public void setName(String name) {
        this.name = name;
        System.out.println("Now service name is: " + this.name);
    }

    private int port;

    public void setPort(int port) {
        if (port == 0) port = DEFAULT_PORT;
        this.port = port;
        System.out.println("Now service port is: " + this.port);
    }

    private int memoryLimit;

    public void setMemoryLimit(int memoryLimit) {
        if (memoryLimit == 0) memoryLimit = 512;
        else if (memoryLimit < MB_MIN_MEMORY_LIMIT || memoryLimit > MB_MAX_MEMORY_LIMIT) {
            if (memoryLimit < MB_MIN_MEMORY_LIMIT)
                System.out.println("Warning, memory limit(mb) is too low than " + MB_MIN_MEMORY_LIMIT);
            else
                System.out.println("Warning, memory limit(mb) is too high than " + MB_MAX_MEMORY_LIMIT);

            System.out.println("Will use min memory limit instead");
            memoryLimit = MB_MIN_MEMORY_LIMIT;
        }

        this.memoryLimit = memoryLimit;
        System.out.printf("Now service memory limit is: %dm\n", this.memoryLimit);
    }

    public Management(String name) {
        this(name, DEFAULT_PORT, MB_MIN_MEMORY_LIMIT);
    }

    public Management(String name, int port, int memoryLimit) {
        this.name = name;
        this.port = port;
        this.memoryLimit = memoryLimit;
    }

    @Override
    public void build() {
        System.out.println("> go build . -o " + this.name);
    }

    @Override
    public void start() {
        System.out.printf(
                "> go run %s.go -p %d -m %s\n",
                this.name, this.port, this.memoryLimit
        );
        System.out.println("App started");

        do {
            showInfo();
        } while (Operating);
    }

    private void actionListener() {
        System.out.println("Action:");
        System.out.println("1. Update service name");
        System.out.println("2. Update service port");
        System.out.println("3. Update service memory limit");
        System.out.println("4. Stop service");
        System.out.println("5. Exit");

        Scanner input = new Scanner(System.in);
        System.out.print("Please input action number: ");
        switch (input.nextLine()) {
            case "1":
                System.out.print("Please input new service name: ");
                this.setName(input.nextLine());
                break;
            case "2":
                System.out.print("Please input new service port: ");
                this.setPort(Integer.parseInt(input.nextLine()));
                break;
            case "3":
                System.out.print("Please input new service memory limit: ");
                this.setMemoryLimit(Integer.parseInt(input.nextLine()));
                break;
            case "4":
                System.out.print("Are you sure? Type the service name to continue: ");
                if (this.name.equals(input.nextLine()))
                    stop();
                else
                    System.out.println("Service name invalid");
                break;
            case "5":
                Operating = false;
                break;
            default:
                System.out.println("Invalid action.");
        }
    }

    @Override
    public void showInfo() {
        System.out.println("\n\n- App Info -");
        System.out.printf(" the app is running with:" +
                        "\n   -name=%s -port=%d -memory--limit=%dm\n\n",
                this.name, this.port, this.memoryLimit);

        actionListener();
    }

    @Override
    public void stop() {
        System.out.printf("> stopping %s service\n\n\n", this.name);
        Operating = false;
    }
}
