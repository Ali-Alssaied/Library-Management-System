package com.example.lbms.service;

import com.example.lbms.dto.BookDto;
import com.example.lbms.dto.PatronDto;
import com.example.lbms.model.entity.Book;
import com.example.lbms.model.entity.Patron;
import com.example.lbms.repository.PatronRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PartonService {
    @Autowired
    private PatronRepo patronRepo;
    @Transactional
    public ResponseEntity<PatronDto> addNewPatron(PatronDto patronDto){
        return ResponseEntity.ok(patronDto.toDto(patronRepo.save(Patron.toEntity(patronDto))));

    }
    public PatronDto updatePatronInfo(PatronDto patronDto){
        return patronDto.toDto(patronRepo.save(Patron.toEntity(patronDto)));

    }
    public void deletePatron(Integer id){
        patronRepo.deleteById(id);

    }

    @Cacheable(value = "patrons")
    public ArrayList<PatronDto> getAllPatrons()
    {
        ArrayList<PatronDto> patron = new ArrayList<>();
        ArrayList<Patron> patrons = new ArrayList<>();
        patrons.addAll(patronRepo.findAll());
        for (Patron pat : patrons) {

            patron.add(PatronDto.toDto(pat));
        }
        return patron;
    }
    @Cacheable(value = "patrons", key = "#id")
    public ResponseEntity<PatronDto> getPatronByID(Integer id){
        Optional<Patron> pat = patronRepo.findById(id);
        if (pat.isPresent()) {
            return ResponseEntity.ok(PatronDto.toDto(pat.get()));
        }
        else
            return null;

    }
}
