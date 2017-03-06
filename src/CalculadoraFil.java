import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by aacerete on 6/03/17.
 */
public class CalculadoraFil extends Thread {

    private final Socket socket;

    public CalculadoraFil(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            Calculadora calc = new Calculadora(in);
            calc.executar();
            calc.enviar(out);
            out.close();
            in.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
