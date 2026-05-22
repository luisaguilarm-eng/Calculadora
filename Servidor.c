#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#ifdef _WIN32
#include <winsock2.h>
#pragma comment(lib, "ws2_32.lib")
#else
#include <unistd.h>
#include <arpa/inet.h>
#endif

int main() {

    // Inicializar Winsock en Windows
    #ifdef _WIN32
    WSADATA wsa;
    WSAStartup(MAKEWORD(2,2), &wsa);
    #endif

    int servidor_fd, cliente_fd;
    struct sockaddr_in servidor, cliente;

    int tam_cliente = sizeof(cliente);

    char buffer[1024];

    // Crear socket del servidor
    servidor_fd = socket(AF_INET, SOCK_STREAM, 0);

    // Configurar IP y puerto
    servidor.sin_family = AF_INET;
    servidor.sin_addr.s_addr = inet_addr("127.0.0.1");
    servidor.sin_port = htons(5000);

    // Asociar servidor al puerto
    bind(
        servidor_fd,
        (struct sockaddr*)&servidor,
        sizeof(servidor)
    );

    // Escuchar conexiones
    listen(servidor_fd, 3);

    printf("Servidor iniciado...\n");
    printf("Esperando cliente...\n");

    // Aceptar conexión
    cliente_fd = accept(
        servidor_fd,
        (struct sockaddr*)&cliente,
        &tam_cliente
    );

    printf("Cliente conectado\n");

    while(1) {

        memset(buffer, 0, sizeof(buffer));

        // Recibir datos
        recv(cliente_fd, buffer, sizeof(buffer), 0);

        // Si no llegan datos, salir
        if(strlen(buffer) == 0) {
            break;
        }

        printf("Datos recibidos: %s\n", buffer);

        int operacion;
        float num1, num2;
        float resultado;

        // Separar datos
        sscanf(buffer, "%d,%f,%f",
               &operacion,
               &num1,
               &num2);

        char respuesta[100];

        // Realizar operación
        switch(operacion) {

            case 1:
                resultado = num1 + num2;
                sprintf(respuesta, "%.2f", resultado);
                break;

            case 2:
                resultado = num1 - num2;
                sprintf(respuesta, "%.2f", resultado);
                break;

            case 3:
                resultado = num1 * num2;
                sprintf(respuesta, "%.2f", resultado);
                break;

            case 4:

                if(num2 != 0) {

                    resultado = num1 / num2;
                    sprintf(respuesta, "%.2f", resultado);

                } else {

                    strcpy(
                        respuesta,
                        "Error: division entre 0"
                    );
                }

                break;

            default:

                strcpy(
                    respuesta,
                    "Operacion invalida"
                );
        }

        // Enviar resultado
        send(
            cliente_fd,
            respuesta,
            strlen(respuesta),
            0
        );
    }

    // Cerrar conexión
    #ifdef _WIN32
    closesocket(cliente_fd);
    closesocket(servidor_fd);
    WSACleanup();
    #else
    close(cliente_fd);
    close(servidor_fd);
    #endif

    return 0;
}