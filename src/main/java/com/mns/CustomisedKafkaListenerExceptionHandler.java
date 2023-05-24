package com.mns;

import io.micronaut.configuration.kafka.exceptions.DefaultKafkaListenerExceptionHandler;
import io.micronaut.configuration.kafka.exceptions.KafkaListenerException;
import io.micronaut.context.annotation.Replaces;
import jakarta.inject.Singleton;

@Singleton
@Replaces(DefaultKafkaListenerExceptionHandler.class)
public class CustomisedKafkaListenerExceptionHandler extends DefaultKafkaListenerExceptionHandler {
    @Override
    public void handle(KafkaListenerException exception) {
        System.out.println("Exception occurred in custom Exception Handler: " + exception.getMessage());
    }
}
