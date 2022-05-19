/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mainPackage;

import DAO.DatosDAO;
import comunicacion.RpcDatosRecibidos;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author orlandocamacho
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException, TimeoutException {
        RpcDatosRecibidos rpc = new RpcDatosRecibidos();
        DatosDAO datos = new DatosDAO();
        
        while (true) {
            rpc.solicitarDatos();
            datos.insertarDatos();
            Thread.sleep(60*1000);
            // rpc enviar datos a registro
        }
    }
    
}
