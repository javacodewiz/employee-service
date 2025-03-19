package com.employee.utils;

import com.employee.dto.EmployeeRequestDTO;
import com.employee.dto.EmployeeResponseDTO;
import com.employee.model.Employee;

public class EmployeeUtils {


    public static Employee mapToModel(EmployeeRequestDTO employee)
    {
        return Employee.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .address(employee.getAddress())
                .gender(employee.getGender())
                .departmentCode(employee.getDepartmentCode())
                .build();
    }


    public static EmployeeResponseDTO mapToDto(Employee employee)
    {
        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .address(employee.getAddress())
                .gender(employee.getGender())
                .departmentCode(employee.getDepartmentCode())
                .build();
    }
}
