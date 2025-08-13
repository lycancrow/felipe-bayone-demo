package com.example.bayonedemobackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tasks {
    @Id
    @GeneratedValue
    private int id;
    private String email ;
    private String title;
    private String date;
    private String task;
}



