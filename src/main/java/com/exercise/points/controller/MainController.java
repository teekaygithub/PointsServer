package com.exercise.points.controller;

import com.exercise.points.model.UserPoints;
import com.exercise.points.service.UserPointsService;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    
    private Logger log = LoggerFactory.getLogger(MainController.class);
    
    @Autowired
    UserPointsService userPointsService;
    
    // Returns every single transaction recorded
    @GetMapping("/api/all")
    private List<UserPoints> getAllTransactions() {
        return userPointsService.getAllTransactions();
    }
    
	// Returns total point balance for each distinct payer
    @GetMapping("/api/balance")
    private Map<String, Integer> getBalanceByUser() {
        return userPointsService.getUserBalance();
    }
    
    // Add points to user account for specific payer and date
    @PostMapping("/api/add")
    private void saveTransaction(@RequestBody UserPoints transaction) {
        log.info("POST request body name: {}", transaction.getName());
        log.info("POST request body date: {}", transaction.getDate());
        userPointsService.saveTransaction(transaction);
    }
    
    // Deduct points from the user account using above constraints and return a list of [payer, points deducted] for each call to spend points
    @PostMapping("/api/spend")
    private List<UserPoints> processTransaction(@RequestParam Integer amount) {
        return userPointsService.processTransaction(amount);
    }
}