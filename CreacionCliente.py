import socket
import tkinter as tk
from tkinter import messagebox

# ---------------- SOCKET ----------------

cliente = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

HOST = '127.0.0.1'
PUERTO = 5000

cliente.connect((HOST, PUERTO))

# ---------------- FUNCIONES ----------------

def enviar_operacion(operacion):

    num1 = entrada1.get()
    num2 = entrada2.get()

    if num1 == "" or num2 == "":
        messagebox.showerror("Error", "Ingresa ambos números")
        return

    mensaje = f"{operacion},{num1},{num2}"

    cliente.send(mensaje.encode())

    respuesta = cliente.recv(1024).decode()

    resultado_label.config(text=f"Resultado: {respuesta}")

# ---------------- VENTANA ----------------

ventana = tk.Tk()
ventana.title("Calculadora Cliente")
ventana.geometry("350x300")

# Título
titulo = tk.Label(ventana, text="Calculadora Cliente-Servidor", font=("Arial", 14))
titulo.pack(pady=10)

# Entrada 1
tk.Label(ventana, text="Primer número").pack()

entrada1 = tk.Entry(ventana)
entrada1.pack()

# Entrada 2
tk.Label(ventana, text="Segundo número").pack()

entrada2 = tk.Entry(ventana)
entrada2.pack(pady=10)

# Botones
frame_botones = tk.Frame(ventana)
frame_botones.pack()

btn_suma = tk.Button(frame_botones, text="+", width=8,
                     command=lambda: enviar_operacion("1"))
btn_suma.grid(row=0, column=0, padx=5, pady=5)

btn_resta = tk.Button(frame_botones, text="-", width=8,
                      command=lambda: enviar_operacion("2"))
btn_resta.grid(row=0, column=1, padx=5, pady=5)

btn_multi = tk.Button(frame_botones, text="*", width=8,
                      command=lambda: enviar_operacion("3"))
btn_multi.grid(row=1, column=0, padx=5, pady=5)

btn_div = tk.Button(frame_botones, text="/", width=8,
                    command=lambda: enviar_operacion("4"))
btn_div.grid(row=1, column=1, padx=5, pady=5)

# Resultado
resultado_label = tk.Label(ventana, text="Resultado: ", font=("Arial", 12))
resultado_label.pack(pady=20)

# Ejecutar ventana
ventana.mainloop()

# Cerrar conexión
cliente.close()