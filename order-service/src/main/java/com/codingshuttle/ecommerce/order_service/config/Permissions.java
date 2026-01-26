package com.codingshuttle.ecommerce.order_service.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@Data
@RefreshScope
public class Permissions {
    @Value("${is.tracking.enabled}")
    private boolean isTrackingEnabled;

    @Value("${my.variable}")
    private String myVariable;
}
