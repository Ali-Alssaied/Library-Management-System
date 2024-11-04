package com.example.lbms.controller;

import com.example.lbms.dto.BookDto;
import com.example.lbms.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookConroller bookController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testAddBook() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setId(1);
        bookDto.setTitle("Test Book");
        bookDto.setAuthor("Author Name");

        when(bookService.addNewBook(any(BookDto.class))).thenReturn(ResponseEntity.ok(bookDto));

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Book\", \"author\":\"Author Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("Author Name"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setId(1);
        bookDto.setTitle("Updated Book");
        bookDto.setAuthor("Updated Author");

        when(bookService.addNewBook(any(BookDto.class))).thenReturn(ResponseEntity.ok(bookDto));

        mockMvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Book\", \"author\":\"Updated Author\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Updated Book"))
                .andExpect(jsonPath("$.author").value("Updated Author"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        doNothing().when(bookService).deleteBook(anyInt());

        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllBooks() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setId(1);
        bookDto.setTitle("Test Book");
        bookDto.setAuthor("Author Name");

        ArrayList<BookDto> books = new ArrayList<>(Collections.singletonList(bookDto));
        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Test Book"))
                .andExpect(jsonPath("$[0].author").value("Author Name"));
    }

    @Test
    public void testGetBookById() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setId(1);
        bookDto.setTitle("Test Book");
        bookDto.setAuthor("Author Name");

        when(bookService.getBookByID(1)).thenReturn(bookDto);

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("Author Name"));
    }
}
