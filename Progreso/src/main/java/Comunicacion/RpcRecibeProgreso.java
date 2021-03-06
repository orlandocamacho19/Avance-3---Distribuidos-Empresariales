package Comunicacion;

import Dominio.Alumno;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RpcRecibeProgreso implements AutoCloseable{

    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_3";
    public String response;

    public RpcRecibeProgreso() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public void solicitarDatos() {
        try ( RpcRecibeProgreso datosProgreso = new RpcRecibeProgreso()) {

            String solicitud = "00000216785";
            System.out.println("Solicitando datos del progreso del alumno...");
            response = datosProgreso.call(solicitud);
            System.out.println(response);
        } catch (IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();

        }
    }

    public String call(String message) throws IOException, InterruptedException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
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
