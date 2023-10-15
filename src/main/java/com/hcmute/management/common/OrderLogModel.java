package com.hcmute.management.common;

import com.hcmute.management.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OrderLogModel {
    private OrderStatus orderStatus;
    private Date time;
    private String description;
}
