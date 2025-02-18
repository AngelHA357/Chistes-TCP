package ejemplosockets;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KnockKnockClientManager implements Runnable {

    private Socket clientSocket;
    private IClienteProxy clienteProxy;

    public KnockKnockClientManager(Socket c) {
        this.clientSocket = c;
        this.clienteProxy = new ClienteProxy();
    }

    @Override
    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            DataInputStream in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));

            String inputLine, outputLine;
            outputLine = clienteProxy.obtenerChiste(null);
            out.writeUTF(outputLine);
            out.flush();

            while (!clientSocket.isClosed()) {
                try {
                    if (in.available() > 0) {
                        inputLine = in.readUTF();
                        outputLine = clienteProxy.obtenerChiste(inputLine);
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
