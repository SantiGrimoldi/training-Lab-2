package com.example.demo.dto;

import com.example.demo.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDto {
    private Long id;
    private  String title;
    private String author;
    private Long publicationYear;
    private String genre;
    private boolean isAvailable;
    private String language;
    private int pages;

    public static BookDto from(Book createBookRequest) {
        return BookDto.builder()
                .id(createBookRequest.getId())
                .title(createBookRequest.getTitle())
                .author(createBookRequest.getAuthor())
                .publicationYear(createBookRequest.getPublicationYear().longValue())
                .genre(createBookRequest.getGenre())
                .isAvailable(createBookRequest.isAvailable())
                .language(createBookRequest.getLanguage())
                .pages(createBookRequest.getPages())
                .build();
    }
}
