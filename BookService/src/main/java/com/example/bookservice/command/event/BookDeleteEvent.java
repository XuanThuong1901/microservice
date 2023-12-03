package com.example.bookservice.command.event;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDeleteEvent {
    private String id;
}
