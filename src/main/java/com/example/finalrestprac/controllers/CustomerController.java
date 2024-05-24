package com.example.finalrestprac.controllers;

import com.example.finalrestprac.dtos.customerdtos.CreateCustomerDto;
import com.example.finalrestprac.dtos.pagedtos.PageDto;
import com.example.finalrestprac.entites.Customer;
import com.example.finalrestprac.exceptions.ItemNotFoundException;
import com.example.finalrestprac.properties.FileStorageProperty;
import com.example.finalrestprac.services.CustomerService;
import com.example.finalrestprac.utils.ListMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    FileStorageProperty fileStorageProperty;
    @Autowired
    ListMapper listMapper;

    @GetMapping("")
    public ResponseEntity<Page<Customer>> getAllCustomers(@RequestParam(defaultValue = "") String custName,
                                                          @RequestParam(defaultValue = "0") @Min(0) Integer creditLimLower,
                                                          @RequestParam(defaultValue = "0") @Min(0) Integer creditLimUpper,
                                                          @RequestParam(required = false) List<String> sortBy,
                                                          @RequestParam(required = false) List<String> dir,
                                                          @RequestParam(defaultValue = "0") Integer page,
                                                          @RequestParam(defaultValue = "10") Integer pageSize
    ) {
//        throw new ItemNotFoundException("not found");
        return ResponseEntity.ok(customerService.queryCustomers(custName, creditLimLower, creditLimUpper, sortBy, dir, page, pageSize));
    }

    @PostMapping("")
    public Customer registerNewCustomer(@RequestBody @Valid CreateCustomerDto createCustomerDto) {
        return customerService.createCustomer(createCustomerDto);
    }

    @GetMapping("/valued")
    public ResponseEntity<PageDto<Customer>> getAllCustomerMTTenOrders(@RequestParam(defaultValue = "0") @Min(0) Integer page, @RequestParam(defaultValue = "10") @Min(1) Integer pageSize){
        return ResponseEntity.ok(listMapper.mapPage(customerService.queryValuedCustomers(page, pageSize), CreateCustomerDto.class, modelMapper));
    }
}
