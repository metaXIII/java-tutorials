package com.metaxiii.fr.controller;

import com.metaxiii.fr.dto.BookDTO;
import com.metaxiii.fr.entity.Book;
import com.metaxiii.fr.exception.BookIdMismatchException;
import com.metaxiii.fr.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;

    @GetMapping
    public Iterable<Book> findAll() {
        log.info("hello");
        return bookService.findAll();
    }

    @GetMapping("/title/{book-title}")
    public List<Book> findByTitle(@PathVariable(name = "book-title") String title) {
        return bookService.findByTitle(title);
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody BookDTO bookDTO) {
        return bookService.save(bookDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.findById(id);
        bookService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody BookDTO bookDTO, @PathVariable Long id) {
        if (!bookDTO.getId().equals(id)) {
            throw new BookIdMismatchException();
        }
        bookService.findById(id);
        return bookService.save(bookDTO);
    }
}
