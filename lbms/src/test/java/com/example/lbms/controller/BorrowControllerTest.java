package com.example.lbms.controller;

import com.example.lbms.dto.BorrowingDto;
import com.example.lbms.service.BorrowingRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class BorrowControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BorrowingRecordService borrowingService;

    @InjectMocks
    private BorrowController borrowController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(borrowController).build();
    }

    @Test
    public void testAddNewBorrowingAction() throws Exception {
        BorrowingDto borrowingDto = new BorrowingDto();

        borrowingDto.setBookId(1);
        borrowingDto.setPartonId(1);
        borrowingDto.setBorrowingDate(new Date(System.currentTimeMillis()));

        ResponseEntity<BorrowingDto> responseEntity = new ResponseEntity<>(borrowingDto, HttpStatus.CREATED);
        when(borrowingService.addNewBorrowingAction(anyInt(), anyInt())).thenReturn(responseEntity);

        mockMvc.perform(post("/api/borrow/1/patron/1"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookId").value(1))
                .andExpect(jsonPath("$.patronId").value(1))
                .andExpect(jsonPath("$.borrowingDate").value(new Date(System.currentTimeMillis())));
    }

    @Test
    public void testReturnBorrowingAction() throws Exception {
        BorrowingDto borrowingDto = new BorrowingDto();
        borrowingDto.setBookId(1);
        borrowingDto.setPartonId(1);
        borrowingDto.setReturnDate(new Date(System.currentTimeMillis()));

        ResponseEntity<BorrowingDto> responseEntity = new ResponseEntity<>(borrowingDto, HttpStatus.OK);
        when(borrowingService.returnBorrowingAction(anyInt(), anyInt())).thenReturn(responseEntity);

        mockMvc.perform(put("/api/borrow/1/patron/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(1))
                .andExpect(jsonPath("$.patronId").value(1))
                .andExpect(jsonPath("$.returnDate").value(new Date(System.currentTimeMillis())));
    }
}
