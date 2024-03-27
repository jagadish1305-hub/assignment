package com.test.assignment.assignment.controllers;

import com.test.assignment.assignment.service.RewardsService;
import com.test.assignment.assignment.service.RewardsSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

  @Autowired
  RewardsService rewardsService;


  @GetMapping(value = "/{customerId}",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RewardsSummary> rewardsSummaryCustomerId(@PathVariable("customerId") Long customerId){
    RewardsSummary customerRewards = rewardsService.getRewardsByCustomerId(customerId);
    return new ResponseEntity<>(customerRewards, HttpStatus.OK);
  }

}
