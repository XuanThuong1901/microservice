package com.example.bookservice.command.event;

import com.example.bookservice.command.data.entity.Book;
import com.example.bookservice.command.data.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookEventsHandler {

    private final BookRepository repository;

    @EventHandler
    public void on(BookCreateEvent event){
        Book book = new Book();
        BeanUtils.copyProperties(event, book);
        repository.save(book);
    }

    @EventHandler
    public void on(BookUpdateEvent event){
        Book book = repository.findById(event.getId()).orElseThrow(null);
        if(book == null)
            return;
        book.setAuthor(event.getAuthor());
        book.setName(event.getName());
        book.setIsReady(event.getIsReady());
        repository.save(book);
    }

    @EventHandler
    public void on(BookDeleteEvent event){
        Book book = repository.findById(event.getId()).orElseThrow(null);
        if(book == null)
            return;
        repository.delete(book);
    }
}
