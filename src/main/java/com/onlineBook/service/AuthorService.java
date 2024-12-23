package com.onlineBook.service;

import java.util.List;
import java.util.Optional;

import com.onlineBook.entity.Author;

public interface AuthorService {
  void addAuthor(Author author);
  void deleteAuthorById(int id);
  Optional<Author> getAuthorById(int id);
  List<Author> getAllAuthors();
}
