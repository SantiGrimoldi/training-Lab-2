package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.CreateBookDto;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;

public interface BookService {

    public BookDto createBook(CreateBookDto createBookRequest);
    
    public BookDto updateBook(Long bookId, BookDto updateBookRequest) throws BadRequestException;

    public Page<BookDto> getAllBooks(int page, int size);

    public BookDto getBookById(Long bookId) throws BadRequestException;

    public void deleteBook(Long bookId) throws BadRequestException;

    public BookDto changeAvailability(Long bookId, boolean available) throws BadRequestException;

}
