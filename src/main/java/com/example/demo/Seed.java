package com.example.demo;

import com.example.demo.dto.CreateBookDto;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class Seed {
    private final BookRepository bookRepository;

    @Autowired
    public Seed(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void seedData() {
        if (bookRepository.count() == 0) {
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                var books = objectMapper.readValue(
                        new File("seed-data/books-seed.json"), 
                        new TypeReference<List<CreateBookDto>>() {}
                );
                books.forEach(bookDto -> {
                    var book = Book.from(bookDto);
                    bookRepository.save(book);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
