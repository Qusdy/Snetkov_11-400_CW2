package udp;


public class Main {
    public static void main(String... args) {

        try (GreetingServer server = new GreetingServer();
             GreetingClient client = new GreetingClient()) {
            server.start();
            System.out.println(client.send("Hello"));
            System.out.println(client.send("bye"));
        }
    }
}
