package com.hcmute.management.collection.embedded;

import com.hcmute.management.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
@Builder
public class OrderLogEmbedded {
    private OrderStatus orderStatus;
    private Date time;
    private String description;
    private ObjectId employeeId;
}
