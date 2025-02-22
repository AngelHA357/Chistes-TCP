/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientesocket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author gilberto.borrego
 */
public class ClienteSocket {
    public static void main(String[] args) {
        try (Socket kkSocket = new Socket("localhost", 4444);
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(kkSocket.getOutputStream()));
            DataInputStream in = new DataInputStream(new BufferedInputStream(kkSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            // Creamos un Stub que tome el input y el output del socket.
            Stub stub = new Stub(in, out);
            String fromServer;
            String fromUser;

            while (true) {
                try {
                    // Leer mensaje del servidor
                    if (in.available() > 0) {
                        // Obtenemos una respuesta del servidor
                        fromServer = stub.obtenerChiste(null);
                        System.out.println("Server: " + fromServer);

                        if (fromServer.equals("Bye.")) {
                            break;  // Salir si el servidor manda "Bye."
                        }

                        // Leer mensaje del usuario
                        fromUser = stdIn.readLine();
                        if (fromUser != null) {
                            // Mandamos el mensaje del usuario.
                            stub.obtenerChiste(fromUser);
                        }
                    }
                } catch (SocketException se) {
                    // Manejo del caso donde el servidor cierra la conexión
                    System.out.println("Conexión cerrada por el servidor.");
                    break;
                }
            }
        } catch (IOException ex) {

        }
    }
}
