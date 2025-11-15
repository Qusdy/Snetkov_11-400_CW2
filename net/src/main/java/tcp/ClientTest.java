package tcp;

public class ClientTest {
    public static void main(String... args) {
        GreetingClient client = new GreetingClient();
        client.start("127.0.0.1", 5555);
        System.out.println(client.send("Hello"));
        System.out.println(client.send("aaa"));
    }
}
