package com.example.lbms.model.entity;

import com.example.lbms.dto.BookDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Book")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    private int publicationYear;
    private String ISBN;

    public static Book toEntity(BookDto bookDto)
    {
        return builder().id(bookDto.getId())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publicationYear(bookDto.getPublicationYear())
                .ISBN(bookDto.getISBN())
                .build();

    }
}
