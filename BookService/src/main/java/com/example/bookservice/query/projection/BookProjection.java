package com.example.bookservice.query.projection;

import com.example.bookservice.command.data.entity.Book;
import com.example.bookservice.command.data.repository.BookRepository;
import com.example.bookservice.query.model.response.BookResponse;
import com.example.bookservice.query.queries.GetAllBookQuery;
import com.example.bookservice.query.queries.GetBookQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookProjection {

    @Autowired
    private final BookRepository repository;

//    @Autowired
//    public BookProjection(BookRepository repository){
//        this.repository = repository;
//    }

    @QueryHandler
    public BookResponse handle(GetBookQuery query){
        Book book = repository.findById(query.getId()).orElse(null);
        BookResponse response = new BookResponse();
        BeanUtils.copyProperties(book, response);
        return response;
    }

    @QueryHandler
    public List<BookResponse> handle(GetAllBookQuery query){
        List<Book> bookList = repository.findAll();

        List<BookResponse> responses = new ArrayList<>();

        bookList.forEach(book -> {
            BookResponse response = new BookResponse();
            BeanUtils.copyProperties(book, response);
            responses.add(response);
        });

        return responses;
    }
}
