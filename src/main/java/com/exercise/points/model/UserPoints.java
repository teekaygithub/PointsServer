package com.exercise.points.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserPoints {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private int id;
    
    @Column
    private String name;
    
    @Column
    private Integer points;
    
    @Column
    private Date date;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName () {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getPoints () {
        return points;
    }
    
    public void setPoints(Integer points) {
        this.points = points;
    }
    
    public Date getDate () {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
}