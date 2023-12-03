package com.example.bookservice.command.model.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
