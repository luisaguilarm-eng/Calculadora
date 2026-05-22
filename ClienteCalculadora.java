import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteCalculadora {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Introduce valor A: ");
        double a = sc.nextDouble();
        System.out.print("Introduce valor B: ");
        double b = sc.nextDouble();
        
        System.out.println("Selecciona operación: 1.Suma, 2.Resta, 3.Mult, 4.Div");
        int opcion = sc.nextInt();
        
        int puerto = 0;
        switch(opcion) {
            case 1: puerto = 5001; break;
            case 2: puerto = 5002; break;
            case 3: puerto = 5003; break;
            case 4: puerto = 5004; break;
        }

        try (Socket socket = new Socket("localhost", puerto);
             DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
             DataInputStream entrada = new DataInputStream(socket.getInputStream())) {

            // Envia los datos
            salida.writeDouble(a);
            salida.writeDouble(b);

            // Recibie la respuesta
            double resultado = entrada.readDouble();
            System.out.println("El resultado devuelto por el servidor es: " + resultado);

        } catch (IOException e) {
            System.out.println("Error: Asegúrate de que el servidor correspondiente esté encendido.");
        }
    }
}