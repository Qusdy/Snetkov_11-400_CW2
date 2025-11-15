package udp;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class GreetingServer extends Thread implements Closeable {

    public static final int PORT = 5556;
    private DatagramSocket socket;
    private boolean alive = true;
    private byte[] buffer = new byte[2048];

    public GreetingServer() {
        try {
            socket = new DatagramSocket(PORT);
            alive = true;
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        while (alive) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                InetAddress address = packet.getAddress();
                int port = packet.getPort();

                String data = ("Your address is " + address.toString());
                byte[] bit = data.getBytes();
                socket.send(new DatagramPacket(bit, bit.length, address, port));
                if ("bye".equalsIgnoreCase(message.trim())) {
                    alive = false;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        close();
    }

    @Override
    public void close() {
        socket.close();
    }
}
