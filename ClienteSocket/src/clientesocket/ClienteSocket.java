/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gilberto.borrego
 */
public class ClienteSocket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
        IServidorProxy envMsj = new ServidorProxy("localhost", 4444);
        envMsj.comunicar();
    }
    
}
