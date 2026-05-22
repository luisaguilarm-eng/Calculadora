import socket

# Crear socket del cliente
cliente = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

HOST = '127.0.0.1'
PUERTO = 5000

# Conectar con servidor
cliente.connect((HOST, PUERTO))

while True:

    print("\n--- CALCULADORA ---")
    print("1. Suma")
    print("2. Resta")
    print("3. Multiplicación")
    print("4. División")
    print("5. Salir")

    opcion = input("Elige una opción: ")

    if opcion == "5":
        break

    num1 = input("Ingresa el primer número: ")
    num2 = input("Ingresa el segundo número: ")

    # Crear mensaje
    mensaje = f"{opcion},{num1},{num2}"

    # Enviar datos
    cliente.send(mensaje.encode())

    # Recibir resultado
    respuesta = cliente.recv(1024).decode()

    print("Resultado:", respuesta)

# Cerrar cliente
cliente.close()