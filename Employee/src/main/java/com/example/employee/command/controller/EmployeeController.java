package com.example.employee.command.controller;

import com.example.employee.command.command.CreateEmployeeCommand;
import com.example.employee.command.model.request.EmployeeRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final CommandGateway commandGateway;

    @PostMapping("")
    public String createEmployee(@RequestBody EmployeeRequest request){
        try {
            CreateEmployeeCommand command = new CreateEmployeeCommand(UUID.randomUUID().toString(), request.getFullName(), request.getKin(), true, new Date());
            commandGateway.sendAndWait(command);
            return "added employee";
        }
        catch (Exception e){
            System.out.println(e);
            return "failed";
        }

    }
}
