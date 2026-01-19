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

    public static void run() throws IOException{
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);
        while(true) {
            try {
                System.out.println("Server is listening on port : " + port);
                Socket accepteConnection = socket.accept();
                System.out.println("Connection accepeted from : " + accepteConnection.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(accepteConnection.getOutputStream());
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(accepteConnection.getInputStream()));
                toClient.println("Server says Heyyy");
                // String clientReq = fromClient.readLine();
                // System.out.println("Req from Client : " + clientReq);

                toClient.close();
                fromClient.close();
                accepteConnection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}