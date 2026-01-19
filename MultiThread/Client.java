import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        for(int i = 0; i < 100; i++) {
            try {
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                int port = 8010;
                try {
                    InetAddress addr = InetAddress.getByName("localhost");
                    Socket socket = new Socket(addr, port);
                    PrintWriter toClient = new PrintWriter(socket.getOutputStream());
                    BufferedReader fromCLient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    toClient.println("Hello from the client!");
                    String line = fromCLient.readLine();
                    System.out.println("response from the socket : " + line);

                    toClient.close();
                    fromCLient.close();
                    socket.close();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
