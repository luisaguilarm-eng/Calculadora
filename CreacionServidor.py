import socket

# Crear socket del servidor
servidor = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# IP y puerto
HOST = '127.0.0.1'
PUERTO = 5000

# Asociar servidor al puerto
servidor.bind((HOST, PUERTO))

# Escuchar conexiones
servidor.listen()

print("Servidor iniciado...")
print("Esperando cliente...")

# Aceptar conexión
cliente, direccion = servidor.accept()

print("Cliente conectado:", direccion)

while True:

    # Recibir datos
    datos = cliente.recv(1024).decode()

    # Si no llegan datos, salir
    if not datos:
        break

    print("Datos recibidos:", datos)

    # Separar datos
    operacion, num1, num2 = datos.split(",")

    num1 = float(num1)
    num2 = float(num2)

    # Realizar operación
    if operacion == "1":
        resultado = num1 + num2

    elif operacion == "2":
        resultado = num1 - num2

    elif operacion == "3":
        resultado = num1 * num2

    elif operacion == "4":

        if num2 != 0:
            resultado = num1 / num2
        else:
            resultado = "Error: división entre 0"

    else:
        resultado = "Operación inválida"

    # Enviar resultado
    cliente.send(str(resultado).encode())

# Cerrar conexión
cliente.close()
servidor.close()