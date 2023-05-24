package com.mns;

import io.micronaut.configuration.kafka.annotation.ErrorStrategy;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;

import static io.micronaut.configuration.kafka.annotation.ErrorStrategyValue.RETRY_ON_ERROR;

@Requires(notEnv = Environment.TEST)
@KafkaListener(value = "ordersGroup", errorStrategy = @ErrorStrategy(value = RETRY_ON_ERROR, retryDelay = "50ms", retryCount=3))

public class AnalyticsListener {

    private final AnalyticsService analyticsService;

    public AnalyticsListener(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @Topic("orders")
    public void updateAnalytics(Order order) {
        analyticsService.updateOrderAnalytics(order);
    }
}