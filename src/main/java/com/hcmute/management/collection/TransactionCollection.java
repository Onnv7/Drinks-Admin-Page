package com.hcmute.management.collection;

import com.hcmute.management.enums.PaymentStatus;
import com.hcmute.management.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "transaction")
public class TransactionCollection {
    @Id
    private String id;
    private String invoiceCode;
    private String timeCode;
    @Builder.Default
    private PaymentStatus status = PaymentStatus.UNPAID;
    private PaymentType paymentType;
    private double totalPaid;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}
