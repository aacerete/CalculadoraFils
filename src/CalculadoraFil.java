import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by aacerete on 6/03/17.
 */
public class CalculadoraFil extends Thread {

    private final Socket socket;

    //constructor a partir de socket
    public CalculadoraFil(Socket socket) {
        this.socket = socket;
    }

    //metode run
    public void run() {

        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());


            while(true) {

                //mentre hi hagi entrada
                if(in.available() > 0) {
                    Calculadora calc = new Calculadora(in);
                    Float resultat = calc.executar();
                    calc.enviar(out, resultat);
                }
            }
            //out.close();
            //in.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
