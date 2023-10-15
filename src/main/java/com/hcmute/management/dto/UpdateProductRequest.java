package com.hcmute.management.dto;

import com.hcmute.management.common.SizeModel;
import com.hcmute.management.common.ToppingModel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.hcmute.management.constant.SwaggerConstant.PRODUCT_PRICE_MIN;

@Data
public class UpdateProductRequest {

    @NotBlank
    private String name;

    @NotEmpty
    private List<MultipartFile> imageList;

    @NotEmpty
    private List<SizeModel> sizeList;

    @NotBlank
    private String description;

    private List<ToppingModel> toppingList;

    @NotNull
    private ObjectId categoryId;
}
