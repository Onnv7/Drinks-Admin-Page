package com.hcmute.management.service;

import com.hcmute.management.collection.CategoryCollection;
import com.hcmute.management.collection.embedded.ImageEmbedded;
import com.hcmute.management.constant.CloudinaryConstant;
import com.hcmute.management.constant.ErrorConstant;
import com.hcmute.management.dto.CreateCategoryRequest;
import com.hcmute.management.dto.GetAllCategoriesResponse;
import com.hcmute.management.dto.GetCategoryByIdResponse;
import com.hcmute.management.dto.UpdateCategoryRequest;
import com.hcmute.management.repository.CategoryRepository;
import com.hcmute.management.utils.CloudinaryUtils;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl {
    private final CategoryRepository categoryRepository;
    private final CloudinaryUtils cloudinaryUtils;
    public CategoryCollection createCategory(CreateCategoryRequest data) throws Exception {
        String cgrName = data.getName();
        CategoryCollection existedCategory = categoryRepository.findByName(cgrName);
        if(existedCategory != null) {
            throw new Exception(ErrorConstant.CATEGORY_EXISTED);
        }
        HashMap<String, String> fileUploaded = cloudinaryUtils.uploadFileToFolder(CloudinaryConstant.CATEGORY_PATH, cgrName, data.getImage());
        ImageEmbedded imageEmbedded = new ImageEmbedded(fileUploaded.get(CloudinaryConstant.PUBLIC_ID), fileUploaded.get(CloudinaryConstant.URL_PROPERTY));
        CategoryCollection category = CategoryCollection.builder()
                .image(imageEmbedded)
                .name(data.getName())
                .build();
        CategoryCollection newCategory = categoryRepository.save(category);
        if(newCategory == null) {
            throw new Exception(ErrorConstant.CREATED_FAILED);
        }
        return category;
    }
    public List<GetAllCategoriesResponse> getAllCategories() {
        return categoryRepository.getAllCategories();
    }
    public GetCategoryByIdResponse getCategoryById(String id) {
        return categoryRepository.getCategoryById(new ObjectId(id));
    }

    public CategoryCollection updateCategory(UpdateCategoryRequest data, String id) throws Exception {
        CategoryCollection category = categoryRepository.findById(id).orElse(null);
        if(category == null) {
            throw new Exception(ErrorConstant.CATEGORY_NOT_FOUND);
        }
        if(!data.getImage().getOriginalFilename().equals("")) {
            cloudinaryUtils.deleteImage(category.getImage().getId());
            HashMap<String, String> fileUploaded = cloudinaryUtils.uploadFileToFolder(CloudinaryConstant.CATEGORY_PATH, data.getName(), data.getImage());
            ImageEmbedded imageEmbedded = new ImageEmbedded(fileUploaded.get(CloudinaryConstant.PUBLIC_ID), fileUploaded.get(CloudinaryConstant.URL_PROPERTY));
            category.setImage(imageEmbedded);
        }
        category.setName(data.getName());
        CategoryCollection newCategory = categoryRepository.save(category);
        if(newCategory != null) {
            return category;
        }
        throw new Exception(ErrorConstant.UPDATE_FAILED);
    }

    public boolean deleteCategoryById(String categoryId) throws Exception {
        CategoryCollection category = categoryRepository.findById(categoryId).orElse(null);
        if(category == null) {
            throw new Exception(ErrorConstant.NOT_FOUND);
        }
        category.setDeleted(true);
        categoryRepository.save(category);
        return true;
    }
    public CategoryCollection exceptionIfNotFoundById(String id) throws Exception {
        CategoryCollection category = categoryRepository.findById(id).orElse(null);
        if(category == null) {
            throw new Exception(ErrorConstant.NOT_FOUND + id);
        }
        return category;
    }


}
