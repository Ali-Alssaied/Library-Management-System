package com.example.lbms.controller;


import com.example.lbms.dto.BorrowingDto;
import com.example.lbms.dto.PatronDto;

import com.example.lbms.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/borrow")

@Controller
public class BorrowController {

    @Autowired
    private BorrowingRecordService borrowingService;

    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingDto> addNewBorrowingAction(@PathVariable int bookId, @PathVariable int patronId) {
        try {
            return borrowingService.addNewBorrowingAction(bookId, patronId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingDto> returnBorrowingAction(@PathVariable int bookId, @PathVariable int patronId) {
        try {
            return borrowingService.returnBorrowingAction(bookId, patronId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}