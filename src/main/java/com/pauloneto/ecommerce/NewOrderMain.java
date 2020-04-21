package com.pauloneto.ecommerce;

import com.pauloneto.ecommerce.dispatcher.KafkaDispatcher;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Inject;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@ApplicationScoped
public class NewOrderMain {

    @Inject
    private Logger logger;

    @Inject
    private KafkaDispatcher dispatcher;


    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            container.select(NewOrderMain.class).get().run();
        }
    }

    public void run() throws ExecutionException, InterruptedException {
        String key = UUID.randomUUID().toString();
        String value = key + ",67523,1234";
        dispatcher.send("ECOMMERCE_NEW_ORDER", key, value);
    }
}
