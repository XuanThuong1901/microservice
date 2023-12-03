package com.example.bookservice.command.event;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateEvent {

    private String id;
    private String name;
    private String author;
    private Boolean isReady;
    private Date upDate;
}
