package com.example.lbms.dto;

import com.example.lbms.model.entity.BorrowingRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowingDto {
    private Integer id;
    private int bookId;
    private int partonId;
    private Date borrowingDate;
    private Date returnDate;

    public static BorrowingDto toDto(BorrowingRecord borrow)
    {
        return  builder().id(borrow.getId())
                .bookId(borrow.getBookId())
                .partonId(borrow.getPartonId())
                .borrowingDate(borrow.getBorrowingDate())
                .returnDate(borrow.getReturnDate())
                .build();

    }
}
