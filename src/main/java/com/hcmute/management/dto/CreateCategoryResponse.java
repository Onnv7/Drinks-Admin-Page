package com.hcmute.management.dto;

import com.hcmute.management.common.ImageModel;
import lombok.Data;

@Data
public class CreateCategoryResponse {
    private String id;
    private String name;
    private ImageModel image;
}