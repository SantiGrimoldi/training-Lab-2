package com.example.demo.service.impl;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.CreateBookDto;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class BookServieImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServieImpl(BookRepository bookRepository1) {
        this.bookRepository = bookRepository1;
    }

    @Override
    public BookDto createBook(CreateBookDto createBookRequest) {
        Book book = Book.from(createBookRequest);
        var createdBook = bookRepository.save(book);
        return BookDto.from(createdBook);
    }

    @Override
    public BookDto updateBook(Long bookId, BookDto updateBookRequest) throws BadRequestException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BadRequestException("Book not found with id: " + bookId));

        book.setTitle(updateBookRequest.getTitle());
        book.setAuthor(updateBookRequest.getAuthor());
        book.setPublicationYear(updateBookRequest.getPublicationYear());
        book.setGenre(updateBookRequest.getGenre());
        book.setAvailable(updateBookRequest.isAvailable());
        book.setLanguage(updateBookRequest.getLanguage());
        book.setPages(updateBookRequest.getPages());

        var updatedBook = bookRepository.save(book);
        return BookDto.from(updatedBook);
    }

    @Override
    public Page<BookDto> getAllBooks(int page, int size) {
        return bookRepository.findAll(org.springframework.data.domain.PageRequest.of(page, size))
                .map(BookDto::from);
    }

    @Override
    public BookDto getBookById(Long bookId) throws BadRequestException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BadRequestException("Book not found with id: " + bookId));
        return BookDto.from(book);
    }

    @Override
    public void deleteBook(Long bookId) throws BadRequestException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BadRequestException("Book not found with id: " + bookId));
        bookRepository.delete(book);
    }

    @Override
    public BookDto changeAvailability(Long bookId, boolean available) throws BadRequestException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BadRequestException("Book not found with id: " + bookId));

        book.setAvailable(available);
        var updatedBook = bookRepository.save(book);
        return BookDto.from(updatedBook);
    }
}
