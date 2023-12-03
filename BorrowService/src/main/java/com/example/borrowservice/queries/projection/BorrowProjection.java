package com.example.borrowservice.queries.projection;

import com.example.borrowservice.command.data.entity.Borrow;
import com.example.borrowservice.command.data.repository.BorrowRepository;
import com.example.borrowservice.queries.model.response.BorrowResponse;
import com.example.borrowservice.queries.query.GetAllBorrow;
import com.example.borrowservice.queries.query.GetBorrow;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowProjection {
    private final BorrowRepository repository;

    @QueryHandler
    public List<BorrowResponse> handle(GetAllBorrow getAllBorrow){
        List<Borrow> borrows = repository.findAll();

        List<BorrowResponse> responses = new ArrayList<>();

        borrows.forEach(borrow -> {
            BorrowResponse response = new BorrowResponse();
            BeanUtils.copyProperties(borrow, response);
            responses.add(response);
        });
        return responses;
    }

    @QueryHandler
    public BorrowResponse handle(GetBorrow getBorrow){

        Borrow borrow = repository.findById(getBorrow.getId()).orElse(null);
        BorrowResponse response = new BorrowResponse();
        BeanUtils.copyProperties(borrow, response);

        return response;
    }
}
