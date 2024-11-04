package com.example.lbms.service;

import com.example.lbms.dto.BookDto;
import com.example.lbms.dto.BorrowingDto;
import com.example.lbms.dto.PatronDto;
import com.example.lbms.model.entity.Book;
import com.example.lbms.model.entity.BorrowingRecord;
import com.example.lbms.model.entity.Patron;
import com.example.lbms.repository.BookRepo;
import com.example.lbms.repository.BorrowingRepo;
import com.example.lbms.repository.PatronRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class BorrowingRecordService {

    @Autowired
    private BorrowingRepo borrowingRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private PatronRepo patronRepo;

    @Transactional
        public ResponseEntity<BorrowingDto> addNewBorrowingAction(int bookId , int patronId) throws Exception {
        ResponseEntity<BorrowingDto> response = ResponseEntity.notFound().build();
        boolean isborrowed = false;
            Book book = bookRepo.findById(bookId)
                    .orElseThrow(() -> new Exception("Book not found with ID: " + bookId));

            Patron patron = patronRepo.findById(patronId)
                    .orElseThrow(() -> new Exception("Patron not found with ID: " + patronId));

                BorrowingDto borrowingDto = new BorrowingDto();
                borrowingDto.setBookId(book.getId());
                borrowingDto.setPartonId(patron.getId());
                borrowingDto.setBorrowingDate(new Date(System.currentTimeMillis()));

        ArrayList<BorrowingDto> blist= getAllBorrowings();
        for (BorrowingDto bor : blist) {

            if ((bor.getBookId() == bookId) && (bor.getReturnDate() == null)) //check if already borrowed
            {
                isborrowed=true;
                break;
            }

        }
        if (isborrowed) {
            response = ResponseEntity.notFound().build();
        }
        else
            response= ResponseEntity.ok(borrowingDto.toDto(borrowingRepo.save(BorrowingRecord.toEntity(borrowingDto))));

        return response;
    }
    @Transactional
    public ResponseEntity<BorrowingDto> returnBorrowingAction(int bookId , int patronId) throws Exception {
        ResponseEntity<BorrowingDto> response = ResponseEntity.notFound().build();
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new Exception("Book not found with ID: " + bookId));

        Patron patron = patronRepo.findById(patronId)
                .orElseThrow(() -> new Exception("Patron not found with ID: " + patronId));
        ArrayList<BorrowingDto> borrowingDtos = new ArrayList<>();
        borrowingDtos = getAllBorrowings();
        for (BorrowingDto bor : borrowingDtos) {

            if ((bor.getBookId() == bookId) && (bor.getPartonId() == patronId)) {
                if ((bor.getBorrowingDate() != null) && (bor.getReturnDate() == null)) {
                    BorrowingDto bdt = new BorrowingDto();
                    bdt.setId(bor.getId());
                    bdt.setBookId(bor.getBookId());
                    bdt.setPartonId(bor.getPartonId());
                    bdt.setBorrowingDate(bor.getBorrowingDate());
                    bdt.setReturnDate(new Date(System.currentTimeMillis()));
                    response= ResponseEntity.ok(BorrowingDto.toDto(borrowingRepo.save(BorrowingRecord.toEntity(bdt))));
                    break;
                } else
                    response= ResponseEntity.notFound().build();
            }

        }
        return response;
    }



        public BorrowingDto updateBorrowingInfo(BorrowingDto borrDto){
            return borrDto.toDto(borrowingRepo.save(BorrowingRecord.toEntity(borrDto)));

        }
        public void deleteBorrowing(Integer id){
            borrowingRepo.deleteById(id);

        }

        public ArrayList<BorrowingDto> getAllBorrowings()
        {
            ArrayList<BorrowingDto> borrowingDto = new ArrayList<>();
            ArrayList<BorrowingRecord> borrowing = new ArrayList<>();
            borrowing.addAll(borrowingRepo.findAll());
            for (BorrowingRecord bor : borrowing) {

                borrowingDto.add(BorrowingDto.toDto(bor));
            }
            return borrowingDto;
        }
    public BorrowingDto getBorrowingByID(Integer id){
        Optional<BorrowingRecord> br = borrowingRepo.findById(id);
        if (br.isPresent()) {
            return BorrowingDto.toDto(br.get());
        }
        else
            return null;

    }
}
