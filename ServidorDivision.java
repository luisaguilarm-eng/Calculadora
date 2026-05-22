import java.io.*;
import java.net.*;

public class ServidorDivision {
    public static void main(String[] args) {
        int puerto = 5004;
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor de DIVISIÓN activo en puerto " + puerto);
            while (true) {
                try (Socket cliente = servidor.accept();
                     DataInputStream entrada = new DataInputStream(cliente.getInputStream());
                     DataOutputStream salida = new DataOutputStream(cliente.getOutputStream())) {
                    
                    double a = entrada.readDouble();
                    double b = entrada.readDouble();
                    
                    if (b == 0) {
                        salida.writeDouble(Double.NaN); // Not a Number para indicar si es q hay error
                    } else {
                        salida.writeDouble(a / b);
                    }
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}