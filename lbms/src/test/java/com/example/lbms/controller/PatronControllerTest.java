package com.example.lbms.controller;

import com.example.lbms.dto.PatronDto;
import com.example.lbms.service.PartonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
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

public class PatronControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PartonService partonService;

    @InjectMocks
    private PatronConroller patronController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(patronController).build();
    }

    @Test
    public void testAddPatron() throws Exception {
        PatronDto patronDto = new PatronDto();
        patronDto.setId(1);
        patronDto.setName("John Doe");
        patronDto.setEmail("john.doe@example.com");

        when(partonService.addNewPatron(any(PatronDto.class))).thenReturn(ResponseEntity.ok(patronDto));

        mockMvc.perform(post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\", \"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    public void testUpdatePatron() throws Exception {
        PatronDto patronDto = new PatronDto();
        patronDto.setId(1);
        patronDto.setName("Updated Patron");
        patronDto.setEmail("updated.patron@example.com");

        when(partonService.addNewPatron(any(PatronDto.class))).thenReturn(ResponseEntity.ok(patronDto));

        mockMvc.perform(put("/api/patrons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Patron\", \"email\":\"updated.patron@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Patron"))
                .andExpect(jsonPath("$.email").value("updated.patron@example.com"));
    }

    @Test
    public void testDeletePatron() throws Exception {
        doNothing().when(partonService).deletePatron(anyInt());

        mockMvc.perform(delete("/api/patrons/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllPatrons() throws Exception {
        PatronDto patronDto = new PatronDto();
        patronDto.setId(1);
        patronDto.setName("John Doe");
        patronDto.setEmail("john.doe@example.com");

        ArrayList<PatronDto> patrons = new ArrayList<>(Collections.singletonList(patronDto));
        when(partonService.getAllPatrons()).thenReturn(patrons);

        mockMvc.perform(get("/api/patrons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"));
    }

    @Test
    public void testGetPatronById() throws Exception {
        PatronDto patronDto = new PatronDto();
        patronDto.setId(1);
        patronDto.setName("John Doe");
        patronDto.setEmail("john.doe@example.com");

        when(partonService.getPatronByID(1)).thenReturn(ResponseEntity.ok(patronDto));

        mockMvc.perform(get("/api/patrons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }
}
