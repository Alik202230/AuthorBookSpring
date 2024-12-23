package com.onlineBook.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.onlineBook.entity.Author;
import com.onlineBook.repository.AuthorRepository;
import com.onlineBook.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{

  private final AuthorRepository authorRepository;

  public AuthorServiceImpl(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Override
  public void addAuthor(Author author) {
    authorRepository.save(author);
  }

  @Override
  public List<Author> getAllAuthors() {
    return this.authorRepository.findAll();
  }

  @Override
  public void deleteAuthorById(int id) {
    this.authorRepository.deleteById(id);
  }

  @Override
  public Optional<Author> getAuthorById(int id) {
    return this.authorRepository.findByIdOrElseThrow(id);
  }
  
}
