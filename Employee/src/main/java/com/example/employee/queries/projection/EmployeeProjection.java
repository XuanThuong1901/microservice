package com.example.employee.queries.projection;

import com.example.employee.command.data.entity.Employee;
import com.example.employee.command.data.repository.EmployeeRepository;
import com.example.employee.queries.model.response.EmployeeResponse;
import com.example.employee.queries.query.GetAllEmployeeQuery;
import com.example.employee.queries.query.GetEmployeeQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeProjection {

    @Autowired
    private final EmployeeRepository repository;

    @QueryHandler
    public List<EmployeeResponse> handle(GetAllEmployeeQuery query){
        List<Employee> employees = repository.findAll();

        List<EmployeeResponse> responses = new ArrayList<>();

        employees.forEach(employee -> {
            EmployeeResponse response = new EmployeeResponse();
            BeanUtils.copyProperties(employee, response);
            responses.add(response);
        });
        return responses;
    }

    @QueryHandler
    public EmployeeResponse handle(GetEmployeeQuery query){
        Employee employee = repository.findById(query.getId()).orElse(null);
        EmployeeResponse response = new EmployeeResponse();
        BeanUtils.copyProperties(employee, response);
        return response;
    }
}
