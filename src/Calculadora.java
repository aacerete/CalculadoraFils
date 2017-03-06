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

    public Calculadora(DataInputStream in) throws IOException {

        byte[] missatge = new byte[in.available()];
        in.read(missatge);
        String MissatgeOperacio = new String(missatge);
        String temp = "";
        String temp2 = "";
        Boolean pass = false;

        for (int i = 0; i < MissatgeOperacio.length(); i++) {

            if(!pass && (MissatgeOperacio.charAt(i) == '+' || MissatgeOperacio.charAt(i) == '-' || MissatgeOperacio.charAt(i) == '/' || MissatgeOperacio.charAt(i) == 'x')){
                operacio = MissatgeOperacio.charAt(i);
                pass = true;

            }else if(!pass){
                temp += MissatgeOperacio.charAt(i);

            }else{
                temp2 += MissatgeOperacio.charAt(i);
            }
        }

        System.out.println(MissatgeOperacio);
        System.out.println("num1 ->"+temp);
        System.out.println("num2 ->"+temp2);
        System.out.println("operacio ->"+operacio);

        num1 = Float.parseFloat(temp);
        num2 = Float.parseFloat(temp2);
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
    public void enviar(DataOutputStream out, Float resultat) throws IOException {
        out.write(new String(resultat+"").getBytes());
    }
}
