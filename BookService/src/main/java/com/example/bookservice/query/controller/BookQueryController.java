package com.example.bookservice.query.controller;

import com.example.bookservice.query.model.response.BookResponse;
import com.example.bookservice.query.queries.GetAllBookQuery;
import com.example.bookservice.query.queries.GetBookQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookQueryController {

    private final QueryGateway queryGateway;

    @GetMapping("/{id}")
    public BookResponse getById(@PathVariable(name = "id") String id){

        GetBookQuery getBookQuery = new GetBookQuery(id);
        BookResponse response = queryGateway.query(getBookQuery, ResponseTypes.instanceOf(BookResponse.class))
                .join();

        return response;
    }

    @GetMapping("")
    public List<BookResponse> getAll(){

        GetAllBookQuery getAllBookQuery = new GetAllBookQuery();
        List<BookResponse> responses = queryGateway.query(getAllBookQuery, ResponseTypes.multipleInstancesOf(BookResponse.class))
                .join();

        return responses;
    }

}
