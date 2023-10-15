package com.hcmute.management.service;

import com.hcmute.management.collection.ProductCollection;
import com.hcmute.management.collection.embedded.ImageEmbedded;
import com.hcmute.management.constant.CloudinaryConstant;
import com.hcmute.management.constant.ErrorConstant;
import com.hcmute.management.dto.GetAllProductsResponse;
import com.hcmute.management.repository.ProductRepository;
import com.hcmute.management.utils.CloudinaryUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl {
    private final ProductRepository productRepository;
    private final CloudinaryUtils cloudinaryUtils;
    private final CategoryServiceImpl categoryService;
    @Autowired
    @Qualifier("modelMapperNotNull")
    private ModelMapper modelMapperNotNull;

    public List<GetAllProductsResponse> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public ProductCollection createProduct(ProductCollection data, List<MultipartFile> images) throws Exception {
        int size = images.size();
        categoryService.exceptionIfNotFoundById(data.getCategoryId().toString());

        List<ImageEmbedded> imagesList = new ArrayList<ImageEmbedded>();
        for (int i = 0; i < size; i++) {
            HashMap<String, String> fileUploaded = cloudinaryUtils.uploadFileToFolder(
                    CloudinaryConstant.PRODUCT_PATH,
                    data.getName() + "_" + (i + 1),
                    images.get(i)
            );
            imagesList.add(new ImageEmbedded(fileUploaded.get(CloudinaryConstant.PUBLIC_ID), fileUploaded.get(CloudinaryConstant.URL_PROPERTY)));
        }

        data.setImageList(imagesList);
        ProductCollection product = productRepository.save(data);
        if (product != null) {
            return product;
        }
        throw new Exception(ErrorConstant.CREATED_FAILED);
    }

    public boolean deleteProductById(String id) throws Exception {
        ProductCollection product = productRepository.findById(id).orElse(null);
        if(product == null) {
            throw new Exception(ErrorConstant.NOT_FOUND);
        }
//        List<ImageEmbedded> images = product.getImageList();
//        int size = images.size();
//        for (int i = 0; i < size; i++) {
//            cloudinaryUtils.deleteImage(images.get(i).getId());
//        }
//        productRepository.deleteById(id);
        product.setDeleted(true);
        productRepository.save(product);
        return true;

    }

    public ProductCollection getProductById(String id) throws Exception {
        return productRepository.findById(id).orElseThrow();
    }

    public ProductCollection updateProductById(ProductCollection data, List<MultipartFile> images) throws Exception {
        ProductCollection product = productRepository.findById(data.getId()).orElse(null);

        if(product == null) {
            throw new Exception(ErrorConstant.NOT_FOUND);
        }

        List<ImageEmbedded> imagesList = new ArrayList<ImageEmbedded>();
        List<ImageEmbedded> oldImages = product.getImageList();
        modelMapperNotNull.map(data, product);
        int size = images.size();

        for (int i = 0; i < oldImages.size(); i++) {
            cloudinaryUtils.deleteImage(oldImages.get(i).getId());
        }
        for (int i = 0; i < size; i++) {
            HashMap<String, String> fileUploaded = cloudinaryUtils.uploadFileToFolder(CloudinaryConstant.PRODUCT_PATH, data.getName() + "_" + (i + 1), images.get(i));
            imagesList.add(new ImageEmbedded(fileUploaded.get(CloudinaryConstant.PUBLIC_ID), fileUploaded.get(CloudinaryConstant.URL_PROPERTY)));
        }
        product.setImageList(imagesList);
        ProductCollection newProduct = productRepository.save(product);
        if(newProduct != null) {
            return newProduct;
        }
        throw new Exception(ErrorConstant.UPDATE_FAILED);
    }
}
