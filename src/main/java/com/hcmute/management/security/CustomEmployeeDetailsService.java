package com.hcmute.management.security;

import com.hcmute.management.collection.EmployeeCollection;
import com.hcmute.management.enums.Role;
import com.hcmute.management.service.EmployeeServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomEmployeeDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeServiceImpl employeeService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeCollection user = employeeService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("ErrorConstant.USER_NOT_FOUND");
        }
//        else if(user.isVerifiedEmail() == false) {
//            throw new Exception(ErrorConstant.EMAIL_UNVERIFIED);
//        }

        List<String> roleNames = Arrays.stream(user.getRoles())
                .map(Role::name)
                .collect(Collectors.toList());

        List<SimpleGrantedAuthority> authorities = roleNames.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return UserPrincipal.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .authorities(authorities)
                .password(user.getPassword())
                .build();
    }
}
