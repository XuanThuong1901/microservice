package com.example.bookservice.command.event;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateEvent {
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
