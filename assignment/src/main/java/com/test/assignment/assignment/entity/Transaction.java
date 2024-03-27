package com.test.assignment.assignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TRANSACTION_ID")
  @Getter
  @Setter
  private Long transactionId;

  @Column(name = "CUSTOMER_ID")
  @Getter
  @Setter
  private Long customerId;

  @Column(name = "TRANSACTION_DATE")

  @Getter
  @Setter
  private Timestamp transactionDate;

  @Column(name = "AMOUNT")
  @Getter
  @Setter
  private double transactionAmount;


}
