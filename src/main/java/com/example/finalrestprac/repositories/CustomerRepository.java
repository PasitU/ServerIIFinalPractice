package com.example.finalrestprac.repositories;

import com.example.finalrestprac.entites.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
//    Page<Customer> findAllByCustomerNameContainsAndCreditLimitBetween(String name, Integer creditLow, Integer creditUp, Sort sort);
    Page<Customer> findAllByCustomerNameContainsAndCreditLimitBetween(String name, Integer creditLow, Integer creditUp, Pageable pageable);

    @Query("select max(c.creditLimit) from Customer c")
    Integer getMaxCred();

    @Query("select c from Customer c where c.customerNumber in (select c1.customerNumber from Customer c1 inner join Order o1 on c1.customerNumber = o1.customerNumber group by c1 having count(*) > 10)")
//    @Query("select c from Customer c where c.customerNumber > 1 group by c")
    Page<Customer> findValuedCustomer(Pageable pageable);
}
