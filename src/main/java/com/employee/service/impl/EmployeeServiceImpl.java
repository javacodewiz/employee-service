package com.employee.service.impl;

import com.employee.dto.EmployeeRequestDTO;
import com.employee.dto.EmployeeResponseDTO;
import com.employee.exception.ResourceAlreadyExistsBussinessException;
import com.employee.exception.ResourceNotFoundBussinessException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import com.employee.utils.EmployeeUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository repository;

    @Override
    public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO dto) {
        log.info("Inside saveEmployee method");
        Optional<Employee> opemp = repository.getByEmail(dto.getEmail());
        if(opemp.isPresent())
        {
            log.info("Inside Exception");
             throw new ResourceAlreadyExistsBussinessException("Record already exists with this email "+dto.getEmail());

        }else {
            log.info("Inside else");
            Employee employee = EmployeeUtils.mapToModel(dto);
            employee=repository.save(employee);
            log.info("Record saved");
            return EmployeeUtils.mapToDto(employee);

        }

    }

    @Override
    public EmployeeResponseDTO updateEmployee(EmployeeRequestDTO dto) {
        log.info("Inside updateEmployee method");
        Employee employee = repository.getByEmail(dto.getEmail()).orElseThrow(()->new ResourceNotFoundBussinessException("Employee not found with this email "+dto.getEmail()));
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setGender(dto.getGender());
        employee.setAddress(dto.getAddress());
        employee.setDepartmentCode(dto.getDepartmentCode());
        employee = repository.save(employee);
        log.info("Record updated");
        return EmployeeUtils.mapToDto(employee);
    }



    @Override
    public EmployeeResponseDTO getEmployeeByEmail(String email) {
        log.info("Inside getEmployeeByEmail method");
        Employee employee = repository.getByEmail(email).orElseThrow(()->new ResourceNotFoundBussinessException("Employee not found with this email "+email));
        log.info("Record found");
        return EmployeeUtils.mapToDto(employee);
    }

    @Override
    public String deleteEmployee(String email) {
        log.info("Inside deleteEmployee method");
        Employee employee = repository.getByEmail(email).orElseThrow(()->new ResourceNotFoundBussinessException("Employee not found with this email "+email));
        repository.delete(employee);
        log.info("Deleting record");
        return "Employee Record deleted successfully";
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployee() {
        log.info("Inside getAllEmployee method");
        List<Employee> employees = repository.findAll();
        log.info("Fetched all records successfully");
        return employees.stream().map(EmployeeUtils::mapToDto).toList();
    }

    @Override
    public Map<String, Long> getEmployeeCountByGender() {
        log.info("Inside getEmployeeCountByGender method");
        List<Employee> employees = repository.findAll();
        log.info("Counted all records successfully");
        return employees.stream().collect(Collectors.groupingByConcurrent(Employee::getGender,Collectors.counting()));
    }

    @Override
    public List<EmployeeResponseDTO> getEmployeeByPagination(int offset, int pageSize, String fieldName) {
        log.info("Inside getEmployeeByPagination method");
        Page<Employee> all = repository.findAll(PageRequest.of(offset, pageSize, Sort.by(fieldName)));
        log.info("Fetched all records successfully");
        return all.stream().map(EmployeeUtils::mapToDto).toList();
    }
}
