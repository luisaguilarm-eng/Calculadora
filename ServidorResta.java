import java.io.*;
import java.net.*;

public class ServidorResta {
    public static void main(String[] args) {
        int puerto = 5002;
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor de RESTA activo en puerto " + puerto);
            while (true) {
                try (Socket cliente = servidor.accept();
                     DataInputStream entrada = new DataInputStream(cliente.getInputStream());
                     DataOutputStream salida = new DataOutputStream(cliente.getOutputStream())) {
                    
                    double a = entrada.readDouble();
                    double b = entrada.readDouble();
                    salida.writeDouble(a - b); // Lógica de resta
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}