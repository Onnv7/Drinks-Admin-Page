package com.hcmute.management.controller;

import com.hcmute.management.collection.EmployeeCollection;
import com.hcmute.management.dto.CreateEmployeeRequest;
import com.hcmute.management.dto.GetEmployeeByIdResponse;
import com.hcmute.management.dto.UpdateEmployeeRequest;
import com.hcmute.management.service.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.hcmute.management.constant.FilePathConstant.EMPLOYEE_PAGE_PATH;
import static com.hcmute.management.constant.RouteConstant.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE_BASE_PATH)
public class EmployeeController {
    private final ModelMapper modelMapper;
    private final EmployeeServiceImpl employeeService;

    @GetMapping()
    public String getEmployeePage(Model model) throws Exception {
        try {
            List<EmployeeCollection> employeeList = employeeService.getAllEmployees();
            model.addAttribute("activeFlag", "employee");
            model.addAttribute("employee_page_type", "index");
            model.addAttribute("employeeList", employeeList);
            return EMPLOYEE_PAGE_PATH;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @GetMapping("/edit/{employeeId}")
    public String getUpdateEmployeePage(Model model, @PathVariable("employeeId") String employeeId) throws Exception {
        try {
            EmployeeCollection employee = employeeService.findById(employeeId);
            GetEmployeeByIdResponse resData = modelMapper.map(employee, GetEmployeeByIdResponse.class);

//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            String formattedDate = sdf.format(employee.getBirthDate());
//            resData.setBirthDate(formattedDate);
            model.addAttribute("activeFlag", "employee");
            model.addAttribute("employee_page_type", "update");
            model.addAttribute("employee", resData);
            model.addAttribute("date", new Date());
            return EMPLOYEE_PAGE_PATH;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @GetMapping("/create")
    public String getCreateEmployeePage(Model model) throws Exception {
        try {
            CreateEmployeeRequest employee = new CreateEmployeeRequest();
            model.addAttribute("activeFlag", "employee");
            model.addAttribute("employee_page_type", "create");
            model.addAttribute("employee", employee);
            return EMPLOYEE_PAGE_PATH;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    // actions =================================================================

    @PostMapping("/create")
    public String createEmployee(@ModelAttribute @Validated CreateEmployeeRequest body) throws Exception {
        try {
            String dateStr = body.getBirthDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date date = dateFormat.parse(dateStr);
            EmployeeCollection employee = modelMapper.map(body, EmployeeCollection.class);
            employee.setBirthDate(date);
            employeeService.registerEmployee(employee);
            return "redirect:" + EMPLOYEE_BASE_PATH;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @PostMapping("/update/{employeeId}")
    public String updateEmployee(@ModelAttribute @Validated UpdateEmployeeRequest body, @PathVariable("employeeId") String employeeId) throws Exception {
        try {
            EmployeeCollection data = modelMapper.map(body, EmployeeCollection.class);
            data.setId(employeeId);
            employeeService.updateEmployee(data);
            return "redirect:" + EMPLOYEE_BASE_PATH;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
