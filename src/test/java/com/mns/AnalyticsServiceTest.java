package com.mns;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class AnalyticsServiceTest {

    @Inject
    AnalyticsService analyticsService;

    @Test
    void testUpdateOrderAnalyticsAndGetAnalytics() {
        Order o1 = new Order("1491950358", "10151");
        Order o2 = new Order("1680502395", "10151");

        analyticsService.updateOrderAnalytics(o1);
        analyticsService.updateOrderAnalytics(o1);
        analyticsService.updateOrderAnalytics(o1);
        analyticsService.updateOrderAnalytics(o2);

        List<OrderAnalytics> analytics = analyticsService.listAnalytics();
        assertEquals(2, analytics.size());

        assertEquals(3, findOrderAnalytics(o1, analytics).getCount());
        assertEquals(1, findOrderAnalytics(o2, analytics).getCount());
    }

    private OrderAnalytics findOrderAnalytics(Order o, List<OrderAnalytics> analytics) {
        return analytics
                .stream()
                .filter(orderAnalytics -> orderAnalytics.getOrderId().equals(o.id()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}