package com.hcmute.management.common;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class ReviewModel {
    @NotBlank
    private double rating;

    private String content;
}
