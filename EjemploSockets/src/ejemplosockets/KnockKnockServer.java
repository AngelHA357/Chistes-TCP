/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplosockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


/**
 *
 * @author gilberto.borrego
 */
public class KnockKnockServer {

    public static void main(String[] args) {      
        try {
            ServerSocket serverSocket = null;
            serverSocket = new ServerSocket(4444);
                        
            Executor service = Executors.newCachedThreadPool();
            
	    System.out.println("Ya estoy escuchando");
            Socket clientSocket = null;
            
            while (true){
                clientSocket = serverSocket.accept();
                System.out.println("Acepté a un cliente");
                service.execute(new KnockKnockClientManager(clientSocket));                                
            }                                    
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }
    }    
}



    

