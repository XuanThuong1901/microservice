package com.example.bookservice.query.model.response;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private String id;
    private String name;
    private String author;
    private Boolean isReady;
    private Date upDate;

}
