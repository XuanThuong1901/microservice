package com.example.bookservice.command.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBookCommand {
    @TargetAggregateIdentifier
    private String id;
}
