package com.example.bookservice.command.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookCommand {

    @TargetAggregateIdentifier
    private String id;
    private String name;
    private String author;
    private Boolean isReady;

}
