package com.example.borrowservice.command.event;

import com.example.borrowservice.command.data.entity.Borrow;
import com.example.borrowservice.command.data.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class BorrowEventHandle {

    private final BorrowRepository repository;

    @EventHandler
    public void on(EventCreateBorrow event){
        Borrow borrow = new Borrow();
        BeanUtils.copyProperties(event, borrow);
        repository.save(borrow);
    }

    @EventHandler
    public void on(EventDeleteBorrow event){
        Borrow borrow = repository.findById(event.getId()).orElse(null);

        if(borrow != null){
            borrow.setReturnDate(new Date());
            repository.save(borrow);
        }

    }
}
