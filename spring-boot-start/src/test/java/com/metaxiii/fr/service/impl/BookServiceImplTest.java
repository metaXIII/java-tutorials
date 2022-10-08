package com.metaxiii.fr.service.impl;

import com.metaxiii.fr.dto.BookDTO;
import com.metaxiii.fr.entity.Book;
import com.metaxiii.fr.exception.BookNotFoundException;
import com.metaxiii.fr.repository.BookRepository;
import com.metaxiii.fr.service.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    BookMapper mapper;

    @Mock
    private BookRepository repository;

    private static BookDTO getBookDTO() {
        return BookDTO
                .builder()
                .id(1L)
                .author("author")
                .title("title")
                .build();
    }

    @Test
    void findAll() {
        assertDoesNotThrow(() -> {
            bookService.findAll();
        });
    }

    @Test
    void findByTitle() {
        assertDoesNotThrow(() -> {
            bookService.findByTitle(anyString());
        });
    }

    @Test
    void findById() {
        assertThrows(BookNotFoundException.class, () -> bookService.findById(1L));
        when(repository.findById(1L)).thenReturn(Optional.of(getBook()));
        assertDoesNotThrow(() -> {
            bookService.findById(1L);
        });
    }

    @Test
    void save() {
        assertDoesNotThrow(() -> {
            when(mapper.toEntity(any(BookDTO.class))).thenReturn(getBook());
            when(repository.save(any(Book.class))).thenReturn(getBook());
            final Book save = bookService.save(getBookDTO());
            assertEquals(1, save.getId());
            assertEquals("author", save.getAuthor());
            assertEquals("title", save.getTitle());
        });
    }

    private static Book getBook() {
        final Book book = new Book();
        book.setId(1L);
        book.setAuthor("author");
        book.setTitle("title");
        return book;
    }

    @Test
    void deleteById() {
        assertDoesNotThrow(() -> bookService.deleteById(1L));
    }
}
