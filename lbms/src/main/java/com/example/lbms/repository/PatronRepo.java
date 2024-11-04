package com.example.lbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lbms.model.entity.Patron;

public interface PatronRepo extends JpaRepository<Patron, Integer> {
}

