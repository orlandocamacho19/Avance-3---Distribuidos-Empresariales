package MainPackage;

import Comunicacion.RpcRecibeProgreso;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {
    
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            try {
                RpcRecibeProgreso rpcPro = new RpcRecibeProgreso();
                rpcPro.solicitarDatos();
                Thread.sleep(60 * 1000);
                
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TimeoutException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
