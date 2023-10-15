package com.hcmute.management.controller;

import com.hcmute.management.collection.CategoryCollection;
import com.hcmute.management.dto.*;
import com.hcmute.management.service.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
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
@RequestMapping(CATEGORY_BASE_PATH)
@RequiredArgsConstructor
public class CategoryController {
    private final ModelMapper modelMapper;
    private final CategoryServiceImpl categoryService;

    // Pages =================================================================================================
    @GetMapping()
    public String getCategoryPage(Model model) {
        try {
            List<GetAllCategoriesResponse> categories = categoryService.getAllCategories();
            model.addAttribute("activeFlag", "category");
            model.addAttribute("category_page_type", "index");
            model.addAttribute("categoryList", categories);
            return CATEGORY_PAGE_PATH;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/create")
    public String getCreateCategoryPage(Model model) {
        try {
            model.addAttribute("activeFlag", "category");
            model.addAttribute("category_page_type", "create");
            return CATEGORY_PAGE_PATH;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/edit/{categoryId}")
    public String getUpdateCategoryPage(Model model, @PathVariable("categoryId") String categoryId) {
        try {
            GetCategoryByIdResponse data = categoryService.getCategoryById(categoryId);
            model.addAttribute("activeFlag", "category");
            model.addAttribute("category_page_type", "update");
            model.addAttribute("category", data);
            return CATEGORY_PAGE_PATH;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Actions =================================================================================================
    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createCategory(@ModelAttribute @Validated CreateCategoryRequest body) {
        try {
            CategoryCollection category = categoryService.createCategory(body);
//            CreateCategoryResponse resData = modelMapper.map(category, CreateCategoryResponse.class);
            return "redirect:" + CATEGORY_BASE_PATH;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(path = "/update/{categoryId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateCategory(@ModelAttribute @Validated UpdateCategoryRequest body,
                                 @PathVariable("categoryId") String id, Model model) {
        try {
            CategoryCollection data = categoryService.updateCategory(body, id);
            return "redirect:" + CATEGORY_BASE_PATH;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(path = "/delete/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") String id, Model model) {
        try {
            categoryService.deleteCategoryById(id);
            return "redirect:" + CATEGORY_BASE_PATH;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
