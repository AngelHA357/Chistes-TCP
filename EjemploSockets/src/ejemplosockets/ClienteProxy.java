/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplosockets;

/**
 *
 * @author JoseH
 */
public class ClienteProxy implements IClienteProxy{
    private KnockKnockProtocol kkp;
    
    public ClienteProxy() {
        this.kkp = new KnockKnockProtocol();
    }
    
    @Override
    public String obtenerChiste(String mensaje){
        return kkp.processInput(mensaje);
    }
}
