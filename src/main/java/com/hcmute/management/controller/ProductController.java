package com.hcmute.management.controller;

import com.hcmute.management.collection.ProductCollection;
import com.hcmute.management.dto.CreateProductRequest;
import com.hcmute.management.dto.GetAllCategoriesResponse;
import com.hcmute.management.dto.GetAllProductsResponse;
import com.hcmute.management.dto.UpdateProductRequest;
import com.hcmute.management.service.CategoryServiceImpl;
import com.hcmute.management.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.hcmute.management.constant.RouteConstant.*;
import static com.hcmute.management.constant.FilePathConstant.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(PRODUCT_BASE_PATH)
public class ProductController {
    private final ModelMapper modelMapper;
    private final ProductServiceImpl productService;
    private final CategoryServiceImpl categoryService;
    @GetMapping()
    public String getProductPage(Model model) {
        List<GetAllProductsResponse> productList = productService.getAllProducts();
        model.addAttribute("activeFlag", "product");
        model.addAttribute("product_page_type", "index");
        model.addAttribute("productList", productList);
        return PRODUCT_PAGE_PATH;
    }

    @GetMapping("/create")
    public String getCreateProductPage(Model model) {
        List<GetAllCategoriesResponse> categories = categoryService.getAllCategories();
        CreateProductRequest createProductRequest = new CreateProductRequest();
        model.addAttribute("activeFlag", "product");
        model.addAttribute("product_page_type", "create");
        model.addAttribute("createProductRequest", createProductRequest);
        model.addAttribute("categories", categories);
        return PRODUCT_PAGE_PATH;
    }

    @GetMapping("/edit/{productId}")
    public String getUpdateProductPage(Model model, @PathVariable("productId") String productId) {
        try {
            ProductCollection product = productService.getProductById(productId);
            List<GetAllCategoriesResponse> categories = categoryService.getAllCategories();

            model.addAttribute("activeFlag", "product");
            model.addAttribute("product_page_type", "update");
            model.addAttribute("product", product);
            model.addAttribute("categories", categories);
            return PRODUCT_PAGE_PATH;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    // actions ======================================================================
    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createProduct(@ModelAttribute @Validated CreateProductRequest body, Model model) {
        try {
            model.addAttribute("activeFlag", "product");
            model.addAttribute("product_page_type", "index");
            ProductCollection product = modelMapper.map(body, ProductCollection.class);
            product = productService.createProduct(product, body.getImageList());

            return "redirect:" + PRODUCT_BASE_PATH;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(Model model, @PathVariable("productId") String productId) {
       try {
           productService.deleteProductById(productId);
           model.addAttribute("activeFlag", "product");
           model.addAttribute("product_page_type", "index");
           return "redirect:" + PRODUCT_BASE_PATH;
       } catch (Exception e) {
           e.printStackTrace();
           throw new RuntimeException(e);
       }
    }

    @PostMapping(path = "/update/{productId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateProductById(@ModelAttribute @Validated UpdateProductRequest body,
                                                         @PathVariable("productId") String id) {
        try {
            ProductCollection data = modelMapper.map(body, ProductCollection.class);
            data.setId(id);
            ProductCollection updatedData = productService.updateProductById(data, body.getImageList());

            return "redirect:" + PRODUCT_BASE_PATH;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
