package com.hcmute.management.service;

import com.hcmute.management.collection.EmployeeCollection;
import com.hcmute.management.constant.ErrorConstant;
import com.hcmute.management.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl {
    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    @Qualifier("modelMapperNotNull")
    private ModelMapper modelMapperNotNull;

    public List<EmployeeCollection> getAllEmployees() throws Exception {
        return employeeRepository.findAll();
    }

    public EmployeeCollection findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    public void registerEmployee(EmployeeCollection data) throws Exception {
        EmployeeCollection existedEmployee = employeeRepository.findByUsername(data.getUsername());
        if (existedEmployee != null) {
            throw new Exception("Username is already registered");
//            throw new Exception("ErrorConstant.REGISTERED_EMAIL");
        }
        EmployeeCollection newEmployee = employeeRepository.save(data);
        if (newEmployee == null) {
            throw new Exception(ErrorConstant.CREATED_FAILED);
        }
    }

    public EmployeeCollection findById(String userId) {
        return employeeRepository.findById(userId).orElseThrow();
    }

    public void updateEmployee(EmployeeCollection data) {
        EmployeeCollection employee = employeeRepository.findById(data.getId()).orElseThrow();
        modelMapperNotNull.map(data, employee);
        employeeRepository.save(employee);
    }

}
