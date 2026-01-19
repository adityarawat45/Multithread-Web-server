import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.run();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run () throws UnknownHostException, IOException{
        try {
            int port = 8010;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
