package com.example.bookservice.command.aggregate;

import com.example.bookservice.command.command.CreateBookCommand;
import com.example.bookservice.command.command.DeleteBookCommand;
import com.example.bookservice.command.command.UpdateBookCommand;
import com.example.bookservice.command.event.BookCreateEvent;
import com.example.bookservice.command.event.BookDeleteEvent;
import com.example.bookservice.command.event.BookUpdateEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Aggregate // Cho biết đây là 1 Aggregate
@NoArgsConstructor
public class BookAggregate {

    @AggregateIdentifier
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
    private Date upDate;

//    public BookAggregate(){}

    //  ---- Create Book ------
    @CommandHandler
    public BookAggregate(CreateBookCommand bookCommand){
        BookCreateEvent bookCreateEvent = new BookCreateEvent();
        BeanUtils.copyProperties(bookCommand, bookCreateEvent);
        AggregateLifecycle.apply(bookCreateEvent);
    }

    @EventSourcingHandler
    public void on(BookCreateEvent event){
        this.id = event.getId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
        this.upDate = event.getUpDate();
    }

    // ---- Update Book ------
    @CommandHandler
    public BookAggregate(UpdateBookCommand bookCommand){
        BookUpdateEvent bookUpdateEvent = new BookUpdateEvent();
        BeanUtils.copyProperties(bookCommand, bookUpdateEvent);
        AggregateLifecycle.apply(bookUpdateEvent);
    }

    @EventSourcingHandler
    public void on(BookUpdateEvent event){
        this.id = event.getId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
    }

    @CommandHandler
    public BookAggregate(DeleteBookCommand bookCommand){
        BookUpdateEvent bookUpdateEvent = new BookUpdateEvent();
        BeanUtils.copyProperties(bookCommand, bookUpdateEvent);
        AggregateLifecycle.apply(bookUpdateEvent);
    }

    @EventSourcingHandler
    public void on(BookDeleteEvent event){
        this.id = event.getId();
    }
}
