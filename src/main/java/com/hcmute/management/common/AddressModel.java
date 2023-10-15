package com.hcmute.management.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static com.hcmute.management.constant.SwaggerConstant.PHONE_NUMBER_REGEX;


@Data
public class AddressModel {
    @NotBlank
    private String details;
    @NotBlank
    private double longitude;

    @NotBlank
    private double latitude;

    private String note;

    @NotBlank
    private String recipientName;

    @NotBlank
    @Pattern(regexp = PHONE_NUMBER_REGEX)
    private String phoneNumber;
}
