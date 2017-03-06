import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by aacerete on 6/03/17.
 */
public class CalculadoraServer {


    public static void main(String[] args) throws IOException {

        //crear server
        System.out.println("Creando servidor");
        ServerSocket serverSocket = new ServerSocket();

        //realitzar bind
        InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
        serverSocket.bind(addr);

        while (true) {

            //Acceptem conexions
            System.out.println("Esperant resposta...");
            Socket socket = serverSocket.accept();
            System.out.println("Conectado");

            //cridwem al fil
            CalculadoraFil cf = new CalculadoraFil(socket);
            cf.start();
        }
    }



}
