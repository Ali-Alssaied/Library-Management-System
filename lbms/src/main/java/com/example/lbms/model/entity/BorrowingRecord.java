package com.example.lbms.model.entity;

import com.example.lbms.dto.BorrowingDto;
import com.example.lbms.dto.PatronDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@Entity
@Table(name="BorrowingRecord")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BorrowingRecord
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int bookId;
    private int partonId;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date borrowingDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date returnDate;

    public static BorrowingRecord toEntity(BorrowingDto borrowingDto)
    {
        return builder().id(borrowingDto.getId())
                .bookId(borrowingDto.getBookId())
                .partonId(borrowingDto.getPartonId())
                .borrowingDate(borrowingDto.getBorrowingDate())
                .returnDate(borrowingDto.getReturnDate())
                .build();

    }

}
