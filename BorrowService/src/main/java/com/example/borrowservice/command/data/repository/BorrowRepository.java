package com.example.borrowservice.command.data.repository;

import com.example.borrowservice.command.data.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, String> {

}
