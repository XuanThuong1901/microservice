package com.example.borrowservice.queries.controller;

import com.example.borrowservice.queries.model.response.BorrowResponse;
import com.example.borrowservice.queries.query.GetAllBorrow;
import com.example.borrowservice.queries.query.GetBorrow;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/borrow")
public class BorrowQueryController {

    private final QueryGateway queryGateway;

    @GetMapping("")
    public List<BorrowResponse> getAll(){
        GetAllBorrow getAllBorrow = new GetAllBorrow();

        List<BorrowResponse> responses = queryGateway.query(getAllBorrow, ResponseTypes.multipleInstancesOf(BorrowResponse.class)).join();
        return responses;
    }

    @GetMapping("{id}")
    public BorrowResponse getOne(@PathVariable(name = "id") String id){
        GetBorrow getBorrow = new GetBorrow(id);

        BorrowResponse response = queryGateway.query(getBorrow, ResponseTypes.instanceOf(BorrowResponse.class)).join();
        return response;
    }
}
