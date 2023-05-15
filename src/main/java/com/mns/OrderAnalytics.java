package com.mns;

import io.micronaut.core.annotation.Creator;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class OrderAnalytics {

    private final String orderId;
    private final long count;

    @Creator
    public OrderAnalytics(String orderId, long count) {
        this.orderId = orderId;
        this.count = count;
    }

    public String getOrderId() {
        return orderId;
    }

    public long getCount() {
        return count;
    }
}