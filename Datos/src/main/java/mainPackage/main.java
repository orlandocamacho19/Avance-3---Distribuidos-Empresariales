package mainPackage;

import Comunicacion.RpcAdministrarProgreso;
import DAO.DatosDAO;
import dominio.Materia;
import com.google.gson.Gson;
import comunicacion.RpcDatosRecibidos;
import comunicacion.RpcEnviarDatosRegistroEscolar;
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
    public static void main(String[] args) throws InterruptedException, IOException, TimeoutException, Exception {
        RpcDatosRecibidos rpc = new RpcDatosRecibidos();
        RpcEnviarDatosRegistroEscolar rpcReg = new RpcEnviarDatosRegistroEscolar();
        //RpcAdministrarProgreso rpcPro = new RpcAdministrarProgreso();
        //RpcAdministrarUsuarios rpcUsr = new RpcAdministrarUsuarios();
        DatosDAO datosDao = new DatosDAO();
        while (true) {
            rpc.solicitarDatos();
            datosDao.insertarDatos();
            Thread.sleep(30*1000);
            datosR.getInstance().setJson(datos.getInstance().getJson());
            rpcReg.enviarDatos();
            
//            Materia materia = new Gson().fromJson(datos.getInstance().getJson(), Materia.class);
//            System.out.println(materia);
//            rpcPro.enviarDatos(materia);
//            rpcUsr.enviarDatos();
        }
    }
    
}
