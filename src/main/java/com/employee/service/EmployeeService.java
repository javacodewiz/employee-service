package com.employee.service;

import com.employee.dto.EmployeeRequestDTO;
import com.employee.dto.EmployeeResponseDTO;

import java.util.List;
import java.util.Map;

public interface EmployeeService {


    public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO dto);
    public EmployeeResponseDTO updateEmployee(EmployeeRequestDTO dto);
    public EmployeeResponseDTO getEmployeeByEmail(String email);
    public String deleteEmployee(String email);
    public List<EmployeeResponseDTO> getAllEmployee();
    public Map<String ,Long> getEmployeeCountByGender();
    public List<EmployeeResponseDTO> getEmployeeByPagination(int offset,int pageSize,String fieldName);

}
