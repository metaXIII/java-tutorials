package com.metaxiii.fr.service;

import com.metaxiii.fr.dto.BookDTO;
import com.metaxiii.fr.entity.Book;
import java.util.List;

public interface BookService {
  Iterable<Book> findAll();

  List<Book> findByTitle(String title);

  Book findById(Long id);

  Book save(BookDTO book);

  void deleteById(Long id);
}
