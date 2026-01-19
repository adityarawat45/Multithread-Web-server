import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> getConsumer(){
        return (clientSocket)-> {
            try {
                PrintWriter toClient =  new PrintWriter(clientSocket.getOutputStream());
                toClient.println("Server says heyyy");

                toClient.close();
                clientSocket.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        };
    }

    public static void main (String[] args) throws IOException{
        int port = 8010;
        Server server = new Server();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port : " + port);
            serverSocket.setSoTimeout(10000);
            while(true) {
                Socket acceptedSocket = serverSocket.accept();
                Thread thread = new Thread(()->server.getConsumer().accept(acceptedSocket));
                thread.start();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
