package com.hcmute.management.dto;

import lombok.Data;

@Data
public class GetCategoryByIdResponse {
    private String id;
    private String name;
    private String thumbnailUrl;
}
