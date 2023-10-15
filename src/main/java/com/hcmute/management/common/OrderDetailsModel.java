package com.hcmute.management.common;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

import static com.hcmute.management.constant.SwaggerConstant.*;

@Data
public class OrderDetailsModel {
    @NotNull
    private ObjectId productId;

    @Min(ORDER_QUANTITY_MIN)
    private int quantity;

    private List<ToppingModel> toppings;


    @Min(PRODUCT_PRICE_MIN)
    private double price;

    @NotNull
    private String size;

    private String note;
}
