package com.hcmute.management.collection;


import com.hcmute.management.collection.embedded.OrderDetailsEmbedded;
import com.hcmute.management.collection.embedded.OrderLogEmbedded;
import com.hcmute.management.collection.embedded.ReviewEmbedded;
import com.hcmute.management.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "order")
public class OrderCollection {
    @Id
    private String id;
    private ObjectId userId;

    private List<OrderDetailsEmbedded> products;
    private String note;

    private long total;
    private OrderType orderType;

    private List<OrderLogEmbedded> eventLogs;

    @Builder.Default
    private ReviewEmbedded review = null;

    private ObjectId transactionId;
    private AddressCollection address;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}
