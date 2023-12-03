package com.example.bookservice.command.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
    private Date upDate;

//    @PrePersist
//    private void pre(){
//        this.upDate = new Date();
//        this.isReady = true;
//    }
}
