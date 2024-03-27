package com.test.assignment.assignment.repository;

import com.test.assignment.assignment.entity.Transaction;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long> {

  List<Transaction> findAllByCustomerIdAndTransactionDateBetween(Long customerId, Timestamp from,Timestamp to);
}
