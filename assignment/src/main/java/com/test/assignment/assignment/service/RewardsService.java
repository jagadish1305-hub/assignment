package com.test.assignment.assignment.service;

import com.test.assignment.assignment.entity.Customer;
import com.test.assignment.assignment.entity.Transaction;
import com.test.assignment.assignment.repository.CustomerRepository;
import com.test.assignment.assignment.repository.TransactionRepository;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardsService {

  public static final int daysInMonths = 30;
  public static int firstRewardLimit = 50;
  public static int secondRewardLimit = 100;

  @Autowired
  TransactionRepository transactionRepository;


  @Autowired
  CustomerRepository customerRepository;

  public RewardsSummary getRewardsByCustomerId(Long customerId) {
    Optional<Customer> customer = customerRepository.findById(customerId);
    if(customer.isEmpty())
    {
      throw new RuntimeException("Invalid / Missing customer Id ");
    }
    Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(daysInMonths);
    Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2*daysInMonths);
    Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3*daysInMonths);

    List<Transaction> lastMonthTransactions = transactionRepository.findAllByCustomerIdAndTransactionDateBetween(
        customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));
    List<Transaction> lastSecondMonthTransactions = transactionRepository
        .findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);
    List<Transaction> lastThirdMonthTransactions = transactionRepository
        .findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTimestamp,
            lastSecondMonthTimestamp);

    Long lastMonthRewardPoints = calculateRewardsPerMonth(lastMonthTransactions);
    Long lastSecondMonthRewardPoints = calculateRewardsPerMonth(lastSecondMonthTransactions);
    Long lastThirdMonthRewardPoints = calculateRewardsPerMonth(lastThirdMonthTransactions);
    RewardsSummary rewardsSummary = new RewardsSummary();
    rewardsSummary.setCustomerId(customerId);
    rewardsSummary.setLastMonthRewardPoints(lastMonthRewardPoints);
    rewardsSummary.setLastSecondMonthRewardPoints(lastSecondMonthRewardPoints);
    rewardsSummary.setLastThirdMonthRewardPoints(lastThirdMonthRewardPoints);
    rewardsSummary.setTotalRewards(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

 return rewardsSummary;
  }


  private Long calculateRewardsPerMonth(List<Transaction> transactions) {
    return transactions.stream().map(this::calculateRewards)
        .mapToLong(r -> r).sum();
  }

  private Long calculateRewards(Transaction t) {
    if (t.getTransactionAmount() > firstRewardLimit && t.getTransactionAmount() <= secondRewardLimit) {
      return Math.round(t.getTransactionAmount() - firstRewardLimit);
    } else if (t.getTransactionAmount() > secondRewardLimit) {
      return Math.round(t.getTransactionAmount() - secondRewardLimit) * 2
          + (secondRewardLimit - firstRewardLimit);
    } else
      return 0l;

  }

  public Timestamp getDateBasedOnOffSetDays(int days) {
    return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
  }
}
