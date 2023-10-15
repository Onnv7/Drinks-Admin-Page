package com.hcmute.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcmute.management.enums.Gender;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

@Data
public class CreateEmployeeRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String birthDate;
}
