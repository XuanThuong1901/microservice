package com.example.bookservice.command.controller;

import com.example.bookservice.command.command.CreateBookCommand;
import com.example.bookservice.command.command.DeleteBookCommand;
import com.example.bookservice.command.command.UpdateBookCommand;
import com.example.bookservice.command.model.request.BookRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {

    private final CommandGateway commandGateway;

    @PostMapping("/add")
    public String addBook(@RequestBody BookRequest request){
        try{
            CreateBookCommand command = new CreateBookCommand(UUID.randomUUID().toString(), request.getName(), request.getAuthor(), true, new Date());
            commandGateway.sendAndWait(command);
            return "added book";
        }
        catch (Exception e){
            System.out.println(e);
            return "failed";
        }
    }

    @PostMapping("/update")
    public String updateBook(@RequestBody BookRequest request){
        try{
            UpdateBookCommand command = new UpdateBookCommand(request.getId(), request.getName(), request.getAuthor(), request.getIsReady());
            commandGateway.sendAndWait(command);
            return "updated book";
        }
        catch (Exception e){
            System.out.println(e);
            return "updated failed";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable(name = "id") String id){
        try{
            DeleteBookCommand command = new DeleteBookCommand(id);
            commandGateway.sendAndWait(command);
            return "deleted book";
        }
        catch (Exception e){
            System.out.println(e);
            return "deleted failed";
        }
    }
}
