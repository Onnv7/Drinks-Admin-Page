package com.hcmute.management.dto;

import com.hcmute.management.enums.Gender;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class GetEmployeeByIdResponse {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private Gender gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private boolean enabled;
}
