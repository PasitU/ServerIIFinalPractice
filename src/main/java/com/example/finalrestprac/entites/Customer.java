package com.example.finalrestprac.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private Integer customerNumber;
    private String customerName;
    private String contactLastName;
    private String contactFirstName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
//    private Integer salesRepEmployeeNumber;
    @ManyToOne
    @JoinColumn(name = "salesRepEmployeeNumber")
    private Employee employee;
    private Double creditLimit;
}
