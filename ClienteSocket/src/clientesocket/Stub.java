/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientesocket;

import Proxy.IProxyChistes;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Valenzuela Parra
 */
public class Stub implements IProxyChistes {

    // Para leer respuestas del servidor.
    private DataInputStream in;
    // Para enviar mensajes del usuario hacia el servidor.
    private DataOutputStream out;

    public Stub(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public String obtenerChiste(String mensaje) {
        try {
            /**
             * Si nos llenga un mensaje null, significa que queremos leer una
             * respuesta del servidor.
             */
            if (mensaje == null) {
                // Leemos del server y lo retornamos.
                String fromServer = in.readUTF();
                return fromServer;
            } else {
                // Si no es null, pues queremos enviar un mensaje.
                out.writeUTF(mensaje);
                out.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(Stub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Conversación finalizada.";
    }
}
