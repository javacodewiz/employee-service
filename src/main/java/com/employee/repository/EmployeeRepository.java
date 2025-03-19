package com.employee.repository;

import com.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {


    @Query(value = "select * from employee e where e.email=?1",nativeQuery = true)
    public Optional<Employee> getByEmail(String email);
}
