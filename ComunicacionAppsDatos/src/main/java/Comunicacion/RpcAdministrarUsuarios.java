package Comunicacion;

import dominio.Alumno;
import dominio.DatosAlumnoProgreso;
import dominio.Materia;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RpcAdministrarUsuarios {
    private static final String RPC_QUEUE_NAME = "rpc_4";
    
    public void enviarDatos(Materia materia) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
            channel.queuePurge(RPC_QUEUE_NAME);

            channel.basicQos(1);

            System.out.println("Esperando solicitud de progreso...");
            
            Object monitor = new Object();
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(delivery.getProperties().getCorrelationId())
                        .build();

                String response = "";
                try {
                    String message = new String(delivery.getBody(), "UTF-8");
                    
                    if (message.contains("padre")) {
                        System.out.println(" [.] Solicitud del inicio de sesion padre");
                        System.out.println(" [.] Autenticando padre...");
                    } else {
                        System.out.println(" [.] Solicitud del inicio de sesion maestro");
                        System.out.println(" [.] Autenticando maestro...");
                    }
                    
                    // Autenticar 
                    // response = "autenticado"
                  
                } catch (RuntimeException e) {
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
            Logger.getLogger(RpcAdministrarProgreso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(RpcAdministrarProgreso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
