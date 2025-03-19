package com.employee.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {



    @NotEmpty(message = "First name is required")
    @Size(min=3, max=20, message = "First name should have between 3 and 20 characters")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Size(min=3, max=7, message = "Last name should have between 3 and 7 characters")
    private String lastName;

    @Email(message = "Invalid email address")
    private String email;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number should be 10 digits")
    private String phoneNumber;


    @NotEmpty(message = "Address is required")
    private String address;

    @NotEmpty(message = "Gender is required")
    private String gender;

    @NotEmpty(message = "Department code is required")
    @Size(min = 4, message = "Department code should have at least 4 characters")
    private String departmentCode;
}
