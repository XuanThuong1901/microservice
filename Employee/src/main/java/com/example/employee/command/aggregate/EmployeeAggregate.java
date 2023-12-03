package com.example.employee.command.aggregate;

import com.example.employee.command.command.CreateEmployeeCommand;
import com.example.employee.command.event.CreateEmployeeEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Aggregate
@NoArgsConstructor
public class EmployeeAggregate {

    @AggregateIdentifier
    private String id;
    private String fullName;
    private String kin;
    private Boolean isDisciplined;
    private Date createdDate;

// Create Employee
    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand employeeCommand){
        CreateEmployeeEvent createEmployeeEvent = new CreateEmployeeEvent();
        BeanUtils.copyProperties(employeeCommand, createEmployeeEvent);
        AggregateLifecycle.apply(createEmployeeEvent);
    }

    @EventSourcingHandler
    public void on(CreateEmployeeEvent event){
        this.id = event.getId();
        this.fullName = event.getFullName();
        this.kin = event.getKin();
        this.isDisciplined = event.getIsDisciplined();
        this.createdDate = event.getCreatedDate();
    }
}
