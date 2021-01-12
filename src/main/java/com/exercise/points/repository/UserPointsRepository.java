package com.exercise.points.repository;

import com.exercise.points.model.UserPoints;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserPointsRepository extends CrudRepository <UserPoints, Integer> {
    
    @Query("SELECT DISTINCT up.name FROM UserPoints up")
    List<String> findDistinctUsers();
	
	@Query("SELECT up FROM UserPoints up WHERE id > ?1")
	List<UserPoints> findRecentTransactions(int id);
    
    @Query("SELECT SUM(points) FROM UserPoints WHERE name = ?1")
    Integer findUserBalance(String name);
}