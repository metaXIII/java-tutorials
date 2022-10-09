package com.metaxiii.fr.service.impl;

import com.metaxiii.fr.dto.BookDTO;
import com.metaxiii.fr.entity.Book;
import com.metaxiii.fr.exception.BookNotFoundException;
import com.metaxiii.fr.repository.BookRepository;
import com.metaxiii.fr.service.BookService;
import com.metaxiii.fr.service.mapper.BookMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    @Override
    public Iterable<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Book> findByTitle(final String title) {
        return repository.findByTitle(title);
    }

    @Override
    public Book findById(final Long id) {
        return repository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public Book save(final BookDTO book) {
        return repository.save(mapper.toEntity(book));
    }

    @Override
    public void deleteById(final Long id) {
        repository.deleteById(id);
    }
}
