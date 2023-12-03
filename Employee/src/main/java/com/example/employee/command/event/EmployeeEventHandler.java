package com.example.employee.command.event;

import com.example.employee.command.data.entity.Employee;
import com.example.employee.command.data.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeEventHandler {

    private final EmployeeRepository repository;

    @EventHandler
    public void on(CreateEmployeeEvent event){
        Employee employee = new Employee();
        BeanUtils.copyProperties(event, employee);
        repository.save(employee);
    }
}
