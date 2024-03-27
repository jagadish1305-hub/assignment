package com.test.assignment.assignment.repository;

import com.test.assignment.assignment.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {

}
