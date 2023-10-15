package com.hcmute.management.common;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.hcmute.management.constant.SwaggerConstant.TOPPING_PRICE_MIN;

@Data
public class ToppingModel {
    @NotBlank
    private String name;

    @Min(TOPPING_PRICE_MIN)
    private double price;
}
