package com.hcmute.management.collection;


import com.hcmute.management.collection.embedded.ImageEmbedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "category")
public class CategoryCollection {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private ImageEmbedded image;


    private boolean deleted = false;
    
    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}
