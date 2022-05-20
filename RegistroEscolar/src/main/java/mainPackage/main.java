package mainPackage;

import comunicacion.RpcRecibirDatosConexRegistro;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {

    public static void main(String[] args) {
        RpcRecibirDatosConexRegistro rpc = new RpcRecibirDatosConexRegistro();

        try {
            rpc.recibirDatos();
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
