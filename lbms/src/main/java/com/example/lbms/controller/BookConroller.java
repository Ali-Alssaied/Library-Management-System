package com.example.lbms.controller;


import com.example.lbms.dto.BookDto;
import com.example.lbms.dto.PatronDto;
import com.example.lbms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/books")
public class BookConroller
{
    @Autowired
    private BookService bookService;

    @PostMapping("")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto book)
    {
        try {
            return bookService.addNewBook(book);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
  }
    @PutMapping("{bookId}")
    public ResponseEntity<BookDto> updateBook(@PathVariable int bookId,@RequestBody BookDto bookDto) {
        try {
            bookDto.setId(bookId);
            return bookService.addNewBook(bookDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @DeleteMapping("{bookId}")
    public void deleteBook(@PathVariable int bookId)
    {
         bookService.deleteBook(bookId);
    }
    @GetMapping("")
    public  ArrayList<BookDto> getAllBooks()
    {
       return bookService.getAllBooks();
    }

    @GetMapping("{bookId}")
    public ResponseEntity<BookDto> getBookByID(@PathVariable int bookId)
    {
        try {
            return ResponseEntity.ok(bookService.getBookByID(bookId));
        }
        catch (Exception e) { return ResponseEntity.badRequest().build(); }
    }
}
