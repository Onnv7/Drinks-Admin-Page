package com.hcmute.management.common;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.hcmute.management.constant.SwaggerConstant.PRODUCT_PRICE_MIN;

@Data
public class SizeModel {
    @NotBlank
    private String size;

    @Min(PRODUCT_PRICE_MIN)
    private double price;
}
