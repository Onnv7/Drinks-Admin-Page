package com.hcmute.management.dto;

import com.hcmute.management.common.ImageModel;
import com.hcmute.management.common.SizeModel;
import com.hcmute.management.common.ToppingModel;
import lombok.Data;

import java.util.List;

@Data
public class GetAllProductsResponse  {
    private String id;
    private String name;
    private double price;
    private String description;
    private String imageUrl;
}
