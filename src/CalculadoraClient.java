import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

/**
 * Created by aacerete on 6/03/17.
 */
public class CalculadoraClient {

    public static void main(String[] args) {

        try{

            System.out.println("Creando socket cliente");

            //Socket de tcp
            Socket cliente = new Socket();

            //socket de udp
            //DatagramSocket udp = new DatagramSocket();

            System.out.println("Estableciendo conexion");

            //localhost = 127.0.0.1
            SocketAddress adress = new InetSocketAddress("localhost", 5555);

            cliente.connect(adress);

            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();
            Boolean esperantResposta = false;
            Scanner scanner = new Scanner(System.in);
            while(true){
                    if (!esperantResposta) {
                        System.out.println("Introdueix una operació: ");
                        String envia = scanner.nextLine();
                        System.out.println(envia);
                        os.write(envia.getBytes());
                        esperantResposta = true;
                    } else {
                        if(is.available() > 0) {
                            byte[] response = new byte[is.available()];
                            is.read(response);
                            String msg = new String(response);
                            System.out.println(msg);
                            esperantResposta=false;
                        }
                    }
            }

        }catch (Exception e){
            e.printStackTrace();

        }
    }
}
