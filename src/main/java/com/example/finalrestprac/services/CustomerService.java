package com.example.finalrestprac.services;

import com.example.finalrestprac.dtos.customerdtos.CreateCustomerDto;
import com.example.finalrestprac.entites.Customer;
import com.example.finalrestprac.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ModelMapper modelMapper;

    public Page<Customer> queryCustomers(String name, Integer creditLimLower, Integer creditLimUpper, List<String> sortBy, List<String> dir, Integer page, Integer pageSize) {
        if(creditLimLower > creditLimUpper){
            Integer temp = creditLimUpper;
            creditLimUpper = creditLimLower;
            creditLimLower = temp;
        }
        if(creditLimUpper == 0){
            creditLimUpper = customerRepository.getMaxCred();
        }

        if(sortBy != null && sortBy.size() > 0) {
            if(sortBy.size() > dir.size()){
                int i = dir.size();
                while(i < sortBy.size()){
                    dir.add("desc");
                    i++;
                }
            }
            List<Sort.Order> orderList = new ArrayList<>();
            for (int i = 0; i < sortBy.size(); i++) {
                orderList.add(new Sort.Order(dir.get(i).toLowerCase().equals("desc") ? Sort.Direction.DESC: Sort.Direction.ASC, sortBy.get(i)));
            }
            Pageable pageable = PageRequest.of(page, pageSize, Sort.by(orderList));
            return customerRepository.findAllByCustomerNameContainsAndCreditLimitBetween(name, creditLimLower, creditLimUpper, pageable);
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        return customerRepository.findAllByCustomerNameContainsAndCreditLimitBetween(name, creditLimLower, creditLimUpper, pageable);
    }

    @Transactional
    public Customer createCustomer(@Valid CreateCustomerDto customer) {
        if (customerRepository.existsById(customer.getCustomerNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Id in used");
        }
        return customerRepository.save(modelMapper.map(customer, Customer.class));
    }

    public Page<Customer> queryValuedCustomers(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return customerRepository.findValuedCustomer(pageable);
    }
}
