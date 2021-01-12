package com.exercise.points.service;

import com.exercise.points.model.UserPoints;
import com.exercise.points.repository.UserPointsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;

@Service
public class UserPointsService {
    
    private Logger log = LoggerFactory.getLogger(UserPointsService.class);
    
    @Autowired
    UserPointsRepository userPointsRepository;
    
    public List<UserPoints> getAllTransactions() {
        List<UserPoints> userPointsList = new ArrayList<>();
        userPointsRepository.findAll().forEach(userPoint -> userPointsList.add(userPoint));
        return userPointsList;
    }
    
    public void saveTransaction (UserPoints transaction) {
        Integer balance = userPointsRepository.findUserBalance(transaction.getName());
        if (balance == null) {balance = 0;}
        
        // Do not allow any transaction that will make a payer's balance go below 0
        if ((balance + transaction.getPoints()) > 0) {
            userPointsRepository.save(transaction);
        } else {
            log.error("Cannot complete transaction, insufficient fund for payer {}", transaction.getName());
        }
    }
    
    public Map<String, Integer> getUserBalance() {
        List<String> users = userPointsRepository.findDistinctUsers();
        Map<String, Integer> userBalance = new HashMap<>();
        for (String user : users) {
            userBalance.put(user, userPointsRepository.findUserBalance(user));
        }
        return userBalance;
    }
    
    public List<UserPoints> processTransaction (Integer amount) {
        Integer totalBalance = 0;
        List<UserPoints> total = getAllTransactions();
        List<UserPoints> deductions = new ArrayList<>();
        Map<String, Integer> accounts = getUserBalance();
        
        // Stop any further calculation if the requested amount exceeds the total points balance
        for (String user : accounts.keySet()) {
            totalBalance += accounts.get(user);
        }
        
        if (amount > totalBalance) {
            log.error("Insufficient balance. Requested: {}, available: {}", amount, totalBalance);
            return deductions;
        }
        
        // There exists sufficient funds, now compute the user-specific deductions
        Integer remaining  = amount;
        String name;
        Integer point = 0;
        Integer delta = 0;
        
        for (String user : accounts.keySet()) {
            UserPoints up = new UserPoints();
            up.setName(user);
            up.setPoints(0);
            up.setDate(new Date());
            deductions.add(up);
        }
        
        while (remaining > 0) {
            UserPoints temp = total.remove(0);
            name = temp.getName();
            point = temp.getPoints();
            
            if (remaining >= point) {
                delta = point;
            } else {
                delta = remaining;
            }
            
            // // Make sure no payer's points go below 0
            // Integer newBalance = accounts.get(name) - delta;
            // if (newBalance < 0) {
                // delta = accounts.get(name);
                // accounts.replace(name, 0);
            // } else {
                // accounts.replace(name, newBalance);
            // }
            
            remaining -= delta;
            log.info("Remaining amount: {}", remaining);
            
            for (UserPoints user : deductions) {
                if (user.getName().equals(name)) {
                    user.setPoints(user.getPoints() - delta);
                }
            }
        }
        
        // Update the new payer balance
        for (UserPoints user : deductions) {
            saveTransaction(user);
        }
        
        return deductions;
    }
}