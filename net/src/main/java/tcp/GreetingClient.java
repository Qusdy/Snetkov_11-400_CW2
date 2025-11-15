package tcp;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class GreetingClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
//            out.flush();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String send(String message) {
        try {
            out.println(message);
//            out.flush();
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
