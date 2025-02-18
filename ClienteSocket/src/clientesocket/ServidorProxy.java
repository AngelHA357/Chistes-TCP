/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JoseH
 */
public class ServidorProxy implements IServidorProxy {

    private String host;
    private int puerto;

    public ServidorProxy(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    @Override
    public void comunicar() {
        try (
                Socket kkSocket = new Socket(host, puerto); DataOutputStream out = new DataOutputStream(new BufferedOutputStream(kkSocket.getOutputStream())); DataInputStream in = new DataInputStream(new BufferedInputStream(kkSocket.getInputStream())); BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            String fromServer;
            String fromUser;

            while (true) {
                try {
                    // Leer mensaje del servidor
                    if (in.available() > 0) {
                        fromServer = in.readUTF();
                        System.out.println("Server: " + fromServer);

                        if (fromServer.equals("Bye.")) {
                            break;  // Salir si el servidor manda "Bye."
                        }

                        // Leer mensaje del usuario
                        fromUser = stdIn.readLine();
                        if (fromUser != null) {
                            System.out.println("Client: " + fromUser);
                            out.writeUTF(fromUser);
                            out.flush();
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
