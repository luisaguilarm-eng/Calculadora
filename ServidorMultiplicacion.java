import java.io.*;
import java.net.*;

public class ServidorMultiplicacion {
    public static void main(String[] args) {
        int puerto = 5003;
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor de MULTIPLICACIÓN activo en puerto " + puerto);
            while (true) {
                try (Socket cliente = servidor.accept();
                     DataInputStream entrada = new DataInputStream(cliente.getInputStream());
                     DataOutputStream salida = new DataOutputStream(cliente.getOutputStream())) {
                    
                    double a = entrada.readDouble();
                    double b = entrada.readDouble();
                    salida.writeDouble(a * b); // Lógica de multiplicación
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}