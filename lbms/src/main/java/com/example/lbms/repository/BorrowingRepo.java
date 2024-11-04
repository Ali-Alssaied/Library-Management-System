package com.example.lbms.repository;

import com.example.lbms.model.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRepo extends JpaRepository<BorrowingRecord, Integer> {
}
