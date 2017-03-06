import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;
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


            System.out.println("Estableciendo conexion");

            //localhost = 127.0.0.1
            SocketAddress adress = new InetSocketAddress("localhost", 5555);

            //conectem
            cliente.connect(adress);

            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();
            Boolean esperantResposta = false;

            Scanner scanner = new Scanner(System.in);


            while(true) {

                Date date = new Date();

                //mentre el server estigui esperant

                if (!esperantResposta) {
                    System.out.println("Introdueix una operació: ( + , - , / , x ) ");
                    String enviat = scanner.nextLine();
                    System.out.println(enviat);
                    os.write(enviat.getBytes());
                    esperantResposta = true;
                    registraLog("Petició de: " + adress + " el dia : " + date.toString());
                    registraLog("Operació: " + enviat + " = " );

                } else {
                    //si el server envia resultat
                    if (is.available() > 0) {
                        byte[] response = new byte[is.available()];
                        is.read(response);
                        String resposta = new String(response);
                        System.out.println(resposta);
                        esperantResposta = false;
                        registraLog("Resultat: " +resposta + "\n");
                    }
                }

            }

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    //Metode per a generar l'arxiu de log
    public static void registraLog(String result) throws IOException {

        File f = new File ("log");

        if(!f.exists()) {
            f.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));

        writer.append(result);
        writer.newLine();
        writer.close();

    }
}
