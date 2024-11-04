package com.example.lbms.dto;


import com.example.lbms.model.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto
{
    private Integer id;
    private String title;
    private String author;
    private int publicationYear;
    private String ISBN;

    public static BookDto toDto(Book book)
    {
        return  builder().id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publicationYear(book.getPublicationYear())
                .ISBN(book.getISBN())
                .build();
    }
}
