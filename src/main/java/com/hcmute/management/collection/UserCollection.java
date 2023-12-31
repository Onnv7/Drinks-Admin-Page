package com.hcmute.management.collection;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hcmute.management.enums.Gender;
import com.hcmute.management.enums.Role;
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
@Document(collection = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCollection {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date birthDate;
    @JsonIgnore
    private String password;
    @Indexed(unique = true)
    private String email;
    private String phoneNumber;

    @Builder.Default
    private Role[] roles = {Role.ROLE_USER};

    @Builder.Default
    private boolean enabled = true;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

}