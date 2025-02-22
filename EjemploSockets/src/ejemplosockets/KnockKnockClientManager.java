package ejemplosockets;

import Proxy.IProxyChistes;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KnockKnockClientManager implements Runnable {

    private Socket clientSocket;
    private IProxyChistes skeleton;

    public KnockKnockClientManager(Socket c) {
        this.clientSocket = c;
        this.skeleton = new Skeleton();
    }

    @Override
    public void run() {
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            DataInputStream in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()))) {
            
            String inputLine, outputLine;
            outputLine = skeleton.obtenerChiste(null);
            out.writeUTF(outputLine);
            out.flush();

            while (!clientSocket.isClosed()) {
                try {
                    if (in.available() > 0) {
                        inputLine = in.readUTF();
                        outputLine = skeleton.obtenerChiste(inputLine);
                        out.writeUTF(outputLine);
                        out.flush();

                        if (outputLine.equals("Bye.")) {
                            break;
                        }
                    }
                } catch (SocketException se) {
                    // Manejo de desconexión inesperada
                    System.out.println("Cliente desconectado inesperadamente.");
                    break;
                }
            }

            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(KnockKnockClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
