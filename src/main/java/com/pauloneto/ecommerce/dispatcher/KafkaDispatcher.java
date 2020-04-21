package com.pauloneto.ecommerce.dispatcher;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@ApplicationScoped
public class KafkaDispatcher implements Closeable {

    @Inject
    private KafkaProducer<String, String> producer;

    @Inject
    private Logger logger;


    public void send(String topic, String key, String value) throws ExecutionException, InterruptedException {
        ProducerRecord record = new ProducerRecord<>(topic, key, value);
        Callback callback = (data, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            logger.info("sucesso enviando " + data.topic() + ":::partition " + data.partition() + "/ offset " + data.offset() + "/ timestamp " + data.timestamp());
        };
        producer.send(record, callback).get();
    }

    @Override
    public void close() throws IOException {
        producer.close();
    }
}
