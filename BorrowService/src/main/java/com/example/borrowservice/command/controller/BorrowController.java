package com.example.borrowservice.command.controller;

import com.example.borrowservice.command.command.CreateBorrowCommand;
import com.example.borrowservice.command.command.DeleteBorrowCommand;
import com.example.borrowservice.command.model.request.BorrowRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/borrow")
public class BorrowController {

    private final CommandGateway commandGateway;

    @PostMapping("")
    public String createBorrow(@RequestBody BorrowRequest request){

        CreateBorrowCommand command = new CreateBorrowCommand(UUID.randomUUID().toString(), request.getBookId(), request.getEmployeeId(), new Date(), null);
        commandGateway.sendAndWait(command);
        return "create borrow";
    }

    @PostMapping("/return/{id}")
    public String returnBorrow(@PathVariable(name = "id") String id){
        DeleteBorrowCommand command = new DeleteBorrowCommand(id);
        commandGateway.sendAndWait(command);
        return "return borrow";
    }
}
