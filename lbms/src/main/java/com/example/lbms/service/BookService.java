package com.example.lbms.service;

import com.example.lbms.dto.BookDto;
import com.example.lbms.model.entity.Book;
import com.example.lbms.repository.BookRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Transactional
    public ResponseEntity<BookDto>addNewBook(BookDto bookDto){
        return ResponseEntity.ok(BookDto.toDto(bookRepo.save(Book.toEntity(bookDto))));

    }
    public BookDto updateBookInfo(BookDto bookDto){
        return BookDto.toDto(bookRepo.save(Book.toEntity(bookDto)));

    }
    public void deleteBook(Integer id){
        bookRepo.deleteById(id);

    }
    @Cacheable(value = "books")
    public ArrayList<BookDto> getAllBooks()
    {
        ArrayList<BookDto> books = new ArrayList<>();
        ArrayList<Book> books1 = new ArrayList<>();
        books1.addAll(bookRepo.findAll());
        for (Book book : books1) {

            books.add(BookDto.toDto(book));
        }
        return books;
    }
    @Cacheable(value = "books", key = "#id")
    public BookDto getBookByID(Integer id){
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()) {
            return BookDto.toDto(book.get());
        }
        else
            return null;

    }
}
