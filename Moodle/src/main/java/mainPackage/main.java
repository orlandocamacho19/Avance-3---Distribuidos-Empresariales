package mainPackage;

import comunicacion.RpcDatosMoodle;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author orlandocamacho
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CrearDatos datos = new CrearDatos();
        RpcDatosMoodle rpc = new RpcDatosMoodle();
        try {
            rpc.enviarDatos(datos.getMateria1());
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
