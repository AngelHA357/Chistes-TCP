/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JoseH
 */
public class ServidorProxy implements IServidorProxy{
    private String host;
    private int puerto;

    public ServidorProxy(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }
    
    @Override
    public void comunicar() {
        String mensaje = "";
        try (Socket kkSocket = new Socket(host, puerto); 
                PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true); 
                BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))){
            
            String fromServer;
            String fromUser;
            while ((fromServer = in.readLine()) != null) {
                mensaje = "Server: " + fromServer;
                System.out.println(mensaje);
                if (fromServer.equals("Bye."))
                    break;
                
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    mensaje = "Client: " + fromUser;
                    System.out.println(mensaje);
                    out.println(fromUser);
                }
            }        
        } catch (IOException ex) {
            Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
