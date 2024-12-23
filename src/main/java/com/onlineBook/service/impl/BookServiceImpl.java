package com.onlineBook.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.onlineBook.entity.Book;
import com.onlineBook.repository.BookRepository;
import com.onlineBook.service.BookService;

@Service
public class BookServiceImpl implements BookService{

  private final BookRepository bookRepository;

  public BookServiceImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public void addBook(Book book) {
    book.setCreatedAt(new Date());
    this.bookRepository.save(book);
  }

  @Override
  public List<Book> getAllBooks() {
    return this.bookRepository.findAll();
  }

  @Override
  public Optional<Book> getBookById(int id) {
    return this.bookRepository.findByIdOrElseThrow(id);
  }
  
}
