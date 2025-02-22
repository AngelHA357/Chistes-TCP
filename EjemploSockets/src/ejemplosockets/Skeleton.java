/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplosockets;

import Proxy.IProxyChistes;

/**
 *
 * @author Diego Valenzuela Parra
 */
public class Skeleton implements IProxyChistes {
    private final KnockKnockProtocol kkp;
    
    public Skeleton() {
        this.kkp = new KnockKnockProtocol();
    }
   
    @Override
    public String obtenerChiste(String mensaje){
        return kkp.processInput(mensaje);
    }
}
