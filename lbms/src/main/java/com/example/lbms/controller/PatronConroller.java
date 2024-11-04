package com.example.lbms.controller;


import aj.org.objectweb.asm.commons.TryCatchBlockSorter;
import com.example.lbms.dto.BookDto;
import com.example.lbms.dto.PatronDto;
import com.example.lbms.service.BookService;
import com.example.lbms.service.PartonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/patrons")

public class PatronConroller
{

    @Autowired
    private PartonService partonService;

    @PostMapping("")
    public ResponseEntity<PatronDto> addPatron(@RequestBody PatronDto patronDto)
    {
        try {
            return partonService.addNewPatron(patronDto);
        }
        catch (Exception e)
        {  return ResponseEntity.badRequest().build();}
  }
    @PutMapping("{patid}")
    public ResponseEntity<PatronDto> updatePatron(@PathVariable int patid,@RequestBody PatronDto patronDto)
    {
        patronDto.setId(patid);
        return partonService.addNewPatron(patronDto);
    }
    @DeleteMapping("{patid}")
    public void deletePatron(@PathVariable int patid)
    {
         partonService.deletePatron(patid);
    }
    @GetMapping("")
    public ArrayList<PatronDto> getAllPatrons()
    {
       return partonService.getAllPatrons();
    }

    @GetMapping("{patid}")
    public ResponseEntity<PatronDto> getPatronByID(@PathVariable int patid)
    {
        try {
            return partonService.getPatronByID(patid);
        }
        catch (Exception e)
        {  return ResponseEntity.badRequest().build();}
    }
}
