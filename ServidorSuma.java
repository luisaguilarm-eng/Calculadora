import java.io.*;
import java.net.*;

public class ServidorSuma {
    public static void main(String[] args) {
        int puerto = 5001;
        
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor de SUMA escuchando en el puerto " + puerto);

            while (true) {
                // Acepta la conexión del cliente
                try (Socket cliente = servidor.accept();
                     DataInputStream entrada = new DataInputStream(cliente.getInputStream());
                     DataOutputStream salida = new DataOutputStream(cliente.getOutputStream())) {

                    // Lee los datos enviados por el cliente
                    double a = entrada.readDouble();
                    double b = entrada.readDouble();
                    
                    double resultado = a + b;
                    
                    // Devuelve el resultado
                    salida.writeDouble(resultado);
                    System.out.println("Operación realizada: " + a + " + " + b + " = " + resultado);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
