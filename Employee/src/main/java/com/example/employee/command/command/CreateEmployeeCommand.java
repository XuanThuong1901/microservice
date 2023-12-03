package com.example.employee.command.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeCommand {

    @TargetAggregateIdentifier
    private String id;
    private String fullName;
    private String kin;
    private Boolean isDisciplined;
    private Date createdDate;
}
