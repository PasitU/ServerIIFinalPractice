package com.example.finalrestprac.dtos.customerdtos;

import com.example.finalrestprac.entites.Employee;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCustomerDto {
    @NotNull
    private Integer customerNumber;
    @NotBlank
    @Size(min = 1, max = 50, message = "data must be in range of 1 to 50 characters")
    private String customerName;
    @NotBlank
    @Size(min = 1, max = 50, message = "data must be in range of 1 to 50 characters")
    private String contactLastName;
    @NotBlank
    @Size(min = 1, max = 50, message = "data must be in range of 1 to 50 characters")
    private String contactFirstName;
    @NotNull
    @Size(min = 1, max = 50, message = "data must be in range of 1 to 50 characters")
    private String phone;
    @NotNull
    @Size(min = 1, max = 50, message = "data must be in range of 1 to 50 characters")
    private String addressLine1;
    @Size(min = 1, max = 50, message = "data must be in range of 1 to 50 characters")
    private String addressLine2;
    @NotNull
    @Size(min = 1, max = 50, message = "data must be in range of 1 to 50 characters")
    private String city;
    @Size(min = 1, max = 50, message = "data must be in range of 1 to 50 characters")
    private String state;
    @Size(min = 1, max = 15, message = "data must be in range of 1 to 15 characters")
    private String postalCode;
    @NotNull
    @Size(min = 1, max = 50, message = "data must be in range of 1 to 50 characters")
    private String country;
    private NewEmployeeDto employee;
    @NotNull
    private Double creditLimit;
}
