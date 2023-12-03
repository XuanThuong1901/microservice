package com.example.borrowservice.command.aggregate;

import com.example.borrowservice.command.command.CreateBorrowCommand;
import com.example.borrowservice.command.command.DeleteBorrowCommand;
import com.example.borrowservice.command.event.EventCreateBorrow;
import com.example.borrowservice.command.event.EventDeleteBorrow;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Aggregate
@NoArgsConstructor
public class BorrowAggregate {

    @AggregateIdentifier
    private String id;
    private String bookId;
    private String employeeId;
    private Date borrowingDate;
    private Date returnDate;

    @CommandHandler
    public BorrowAggregate(CreateBorrowCommand command){
        EventCreateBorrow event = new EventCreateBorrow();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(EventCreateBorrow event){
        this.id = event.getId();
        this.bookId = event.getBookId();
        this.employeeId = event.getEmployeeId();
        this.borrowingDate = event.getBorrowingDate();
        this.returnDate = event.getReturnDate();
    }

    @CommandHandler
    public BorrowAggregate(DeleteBorrowCommand command){
        EventDeleteBorrow event = new EventDeleteBorrow(command.getId());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(EventDeleteBorrow event){
        this.id = event.getId();
    }

}
