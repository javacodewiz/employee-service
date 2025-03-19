package com.employee.controller;

import com.employee.dto.EmployeeRequestDTO;
import com.employee.dto.EmployeeResponseDTO;
import com.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
@Validated
public class EmployeeRestController {


    private EmployeeService service;



    @Operation(summary = "Add a new employee", description = "Add a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = EmployeeResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> saveEmployee(@RequestBody @Valid EmployeeRequestDTO dto){
            return ResponseEntity.status(HttpStatus.CREATED).body(service.saveEmployee(dto));
    }


    @Operation(summary = "Update an existing employee", description = "Update an existing employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EmployeeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PutMapping
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@RequestBody @Valid EmployeeRequestDTO dto){
        return ResponseEntity.ok(service.updateEmployee(dto));
    }


    @Operation(summary = "Get employee by id",description = "Get employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success",content = @Content(schema = @Schema(implementation = EmployeeResponseDTO.class))),
            @ApiResponse(responseCode = "404",description = "Not found",content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500",description = "Internal server error",content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/{email}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeByEmail(@PathVariable  String email){
        return ResponseEntity.ok(service.getEmployeeByEmail(email));
    }


    @Operation(summary = "Delete a employee", description = "Delete a employee by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success",content = @Content(schema = @Schema(implementation = EmployeeResponseDTO.class))),
            @ApiResponse(responseCode = "404",description = "Not found",content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500",description = "Internal server error",content = @Content(schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String email){
        return ResponseEntity.ok(service.deleteEmployee(email));
    }


    @Operation(summary = "Get all employees", description = "Get all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success",content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500",description = "Internal server error",content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployee(){
        return ResponseEntity.ok(service.getAllEmployee());
    }


    @Operation(summary = "Get employee count by gender", description = "Get employee count by gender")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success",content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "500",description = "Internal server error",content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/count")
    public ResponseEntity<Map<String ,Long>> getEmployeeCountByGender(){
        return ResponseEntity.ok(service.getEmployeeCountByGender());
    }

    @Operation(summary = "Get employee by pagination", description = "Get employee by pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success",content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500",description = "Internal server error",content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/pagination/{offset}/{pageSize}/{fieldName}")
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployeeByPagination(int offset,int pageSize,String fieldName){
        return ResponseEntity.ok(service.getEmployeeByPagination(offset, pageSize, fieldName));
    }
}
