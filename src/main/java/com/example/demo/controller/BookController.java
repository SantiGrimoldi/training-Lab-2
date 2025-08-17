package com.example.demo.controller;


import com.example.demo.dto.BookDto;
import com.example.demo.dto.CreateBookDto;
import com.example.demo.service.BookService;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody CreateBookDto createBookRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.bookService.createBook(createBookRequest));
    }

    @PostMapping("/update")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto updateBookRequest) throws BadRequestException {
        BookDto updatedBook = this.bookService.updateBook(updateBookRequest.getId(), updateBookRequest);
        return ResponseEntity.ok(updatedBook);
    }

    @PostMapping("/getAll")
    public ResponseEntity<Page<BookDto>> getAllBooks(@RequestParam int page, @RequestParam int size) {
        Page<BookDto> books = this.bookService.getAllBooks(page, size);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long bookId) throws BadRequestException {
        BookDto book = this.bookService.getBookById(bookId);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) throws BadRequestException {
        this.bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{bookId}/changeAvailability")
    public ResponseEntity<BookDto> changeAvailability(@PathVariable Long bookId, @RequestParam boolean available) throws BadRequestException {
        BookDto updatedBook = this.bookService.changeAvailability(bookId, available);
        return ResponseEntity.ok(updatedBook);
    }

}
