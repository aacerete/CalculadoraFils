import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by aacerete on 6/03/17.
 */
public class Calculadora {

    private float num1;
    private float num2;
    private char operacio;
    private float resultat;

    public Calculadora(DataInputStream in) throws IOException {
        num1 = in.readFloat();
        num2 = in.readFloat();
        operacio = in.readChar();
    }
    public float executar() {
        float resultat = 0;
        if(operacio == '+') {
            resultat = num1 + num2;
        }
        if(operacio == '-') {
            resultat = num1 - num2;
        }
        if(operacio == '/') {
            resultat = num1 / num2;
        }
        if(operacio == 'x') {
            resultat = num1 * num2;
        }
        return resultat;
    }
    public void enviar(DataOutputStream out) throws IOException {
        out.writeFloat(resultat);
    }
}
