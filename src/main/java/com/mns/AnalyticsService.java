package com.mns;

import jakarta.inject.Singleton;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Singleton
public class AnalyticsService {

    private final Map<Order, Long> orderAnalytics = new ConcurrentHashMap<>();

    public void updateOrderAnalytics(Order order) {
        orderAnalytics.compute(order, (k, v) -> {
            return v == null ? 1L : v + 1;
        });
    }

    public List<OrderAnalytics> listAnalytics() {
        return orderAnalytics
                .entrySet()
                .stream()
                .map(e -> new OrderAnalytics(e.getKey().getId(), e.getValue()))
                .collect(Collectors.toList());
    }
}