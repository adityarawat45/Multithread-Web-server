import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            run();
        } catch(Exception e) {
            e.printStackTrace();
        } 
    }

    public static void run() {
        int port = 8010;

        while(true) {
            try {
                ServerSocket socket = new ServerSocket(port);
                socket.setSoTimeout(10000);
                System.out.println("Server is listening on port : " + port);
                Socket accepteConnection = socket.accept();
                System.out.println("Connection accepeted from : " + accepteConnection.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(accepteConnection.getOutputStream());
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(accepteConnection.getInputStream()));
                toClient.println("Static response from the server");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}