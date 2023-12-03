package com.example.employee.command.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    private String id;
    private String fullName;
    private String kin;
    private Boolean isDisciplined;
    private Date createdDate;

//    @PrePersist
//    private void pre(){
//        this.createdDate = new Date();
//    }
}
