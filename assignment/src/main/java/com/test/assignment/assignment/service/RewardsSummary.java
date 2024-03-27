package com.test.assignment.assignment.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RewardsSummary {
  private long customerId;
  private long lastMonthRewardPoints;
  private long lastSecondMonthRewardPoints;
  private long lastThirdMonthRewardPoints;
  private long totalRewards;
}
