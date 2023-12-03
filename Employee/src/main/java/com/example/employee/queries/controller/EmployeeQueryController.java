package com.example.employee.queries.controller;

import com.example.employee.queries.model.response.EmployeeResponse;
import com.example.employee.queries.query.GetAllEmployeeQuery;
import com.example.employee.queries.query.GetEmployeeQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeQueryController {

    private final QueryGateway queryGateway;

    @GetMapping("")
    public List<EmployeeResponse> getAll(){
        GetAllEmployeeQuery getAllEmployee = new GetAllEmployeeQuery();

        List<EmployeeResponse> response = queryGateway.query(getAllEmployee, ResponseTypes.multipleInstancesOf(EmployeeResponse.class))
                .join();
        return response;
    }

    @GetMapping("/{id}")
    public EmployeeResponse getOne(@PathVariable(name = "id") String id){
        GetEmployeeQuery getEmployeeQuery = new GetEmployeeQuery(id);

        EmployeeResponse response = queryGateway.query(getEmployeeQuery, ResponseTypes.instanceOf(EmployeeResponse.class))
                .join();

        return response;
    }
}
