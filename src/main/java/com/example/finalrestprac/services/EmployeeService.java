package com.example.finalrestprac.services;

import com.example.finalrestprac.entites.Employee;
import com.example.finalrestprac.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee queryById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "The employee with id " + id + " does not exist."));
    }
}
