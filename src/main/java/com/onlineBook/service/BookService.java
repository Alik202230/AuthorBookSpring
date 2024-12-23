package com.onlineBook.service;

import java.util.List;
import java.util.Optional;

import com.onlineBook.entity.Book;

public interface BookService {
  void addBook(Book book);
  List<Book> getAllBooks();
  Optional<Book> getBookById(int id); 
}

