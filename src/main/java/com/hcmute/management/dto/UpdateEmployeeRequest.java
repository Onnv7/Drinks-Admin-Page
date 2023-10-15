package com.hcmute.management.dto;

import com.hcmute.management.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UpdateEmployeeRequest {

    private String username;

    private String firstName;

    private String lastName;

    private Gender gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    private boolean enabled;
}
