package com.pauloneto.ecommerce.cdiproducer;

import com.pauloneto.ecommerce.enums.KafkaConfigProperties;
import org.apache.kafka.clients.producer.KafkaProducer;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

public class CDIProducer {

    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    @Produces
    public KafkaProducer<String,String> produceKafkaProducer(InjectionPoint injectionPoint){
        return new KafkaProducer<String, String>(KafkaConfigProperties.DEFAULT.getProperties());
    }
}
