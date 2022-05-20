package comunicacion;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import mainPackage.datos;
import seguridad.Encriptacion;

public class RpcDatosRecibidos implements AutoCloseable {

    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_1";
    public String response;

    public RpcDatosRecibidos() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public void solicitarDatos() {
        try (RpcDatosRecibidos datosR = new RpcDatosRecibidos()) {
            
                String solicitud = "Datos moodle";
                System.out.println(" [x] Solicitando datos...");
                response = datosR.call(solicitud);
                System.out.println(response);
                
                Encriptacion seguridad = new Encriptacion();
                response = seguridad.desencriptar(response, "Dia");
                
                datos.getInstance().setJson(response);
            
        } catch (IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RpcDatosRecibidos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(RpcDatosRecibidos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(RpcDatosRecibidos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(RpcDatosRecibidos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(RpcDatosRecibidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String call(String message) throws IOException, InterruptedException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));

        final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);

        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response.offer(new String(delivery.getBody(), "UTF-8"));
            }
        }, consumerTag -> {
        });

        String result = response.take();
        channel.basicCancel(ctag);
        return result;
    }

    public void close() throws IOException {
        connection.close();
    }
}