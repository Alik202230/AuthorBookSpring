package com.onlineBook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.onlineBook.entity.Author;
import com.onlineBook.specification.SearchCriteria;

public interface AuthorService {
  void addAuthor(Author author);
  void deleteAuthorById(int id);
  Optional<Author> getAuthorById(int id);
  List<Author> getAllAuthors();
  Page<Author> getAllAuthors(Pageable pageable);
  List<Author> search(String keyword);
  List<Author> filter(SearchCriteria criteria);
}
