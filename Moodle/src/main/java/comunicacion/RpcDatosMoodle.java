package comunicacion;

import com.rabbitmq.client.*;
import dominio.Materia;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import seguridad.Encriptacion;

public class RpcDatosMoodle {

    private static final String RPC_QUEUE_NAME = "rpc_1";

    public void enviarDatos(Materia datos) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
            channel.queuePurge(RPC_QUEUE_NAME);

            channel.basicQos(1);

            System.out.println("Esperando solicitudes...");

            Object monitor = new Object();
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(delivery.getProperties().getCorrelationId())
                        .build();

                String response = "";
                   
                try {
                    String message = new String(delivery.getBody(), "UTF-8");
      
                    System.out.println(" [.] Solicitud: " + message);
                    System.out.println(" [.] Enviando datos...");
                    response = datos.getJson();
                    Encriptacion seguridad = new Encriptacion();
                    response = seguridad.encriptar(response, "Dia");
                    
                } catch (RuntimeException e) {
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(RpcDatosMoodle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(RpcDatosMoodle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeyException ex) {
                    Logger.getLogger(RpcDatosMoodle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchPaddingException ex) {
                    Logger.getLogger(RpcDatosMoodle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalBlockSizeException ex) {
                    Logger.getLogger(RpcDatosMoodle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BadPaddingException ex) {
                    Logger.getLogger(RpcDatosMoodle.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, response.getBytes("UTF-8"));
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    // RabbitMq consumer worker thread notifies the RPC server owner thread
                    synchronized (monitor) {
                        monitor.notify();
                    }
                }
            };

            channel.basicConsume(RPC_QUEUE_NAME, false, deliverCallback, (consumerTag -> { }));
            // Wait and be prepared to consume the message from RPC client.
            while (true) {
                synchronized (monitor) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(RpcDatosMoodle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(RpcDatosMoodle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}