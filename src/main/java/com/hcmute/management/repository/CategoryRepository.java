package com.hcmute.management.repository;

import com.hcmute.management.collection.CategoryCollection;
import com.hcmute.management.dto.GetAllCategoriesResponse;
import com.hcmute.management.dto.GetCategoryByIdResponse;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository  extends MongoRepository<CategoryCollection, String> {

    CategoryCollection findByName(String name);
    @Aggregation(pipeline = {
            "{$match: { deleted: false }}",
            "{$project: {name: 1, thumbnailUrl: '$image.url'}}"
    })
    List<GetAllCategoriesResponse> getAllCategories();
    @Aggregation(pipeline = {
            "{$match: {_id: ?0}}",
            "{$project: {name: 1, thumbnailUrl: '$image.url'}}"
    })
    GetCategoryByIdResponse getCategoryById(ObjectId id);
}
