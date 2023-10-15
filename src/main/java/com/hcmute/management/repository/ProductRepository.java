package com.hcmute.management.repository;

import com.hcmute.management.collection.ProductCollection;
import com.hcmute.management.dto.GetAllProductsResponse;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductCollection, String> {
    @Aggregation(pipeline = {
            "{$match: { deleted: false }}",
            "{$project: {_id: 1, name: 1, description: 1, price: {$min: '$sizeList.price'}, imageUrl: {$first: '$imageList.url'}}}"
    })
    List<GetAllProductsResponse> getAllProducts();
}
