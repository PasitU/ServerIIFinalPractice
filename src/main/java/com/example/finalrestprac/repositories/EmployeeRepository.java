package com.example.finalrestprac.repositories;

import com.example.finalrestprac.entites.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
