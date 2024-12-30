package com.onlineBook.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.onlineBook.entity.Author;
import com.onlineBook.repository.AuthorRepository;
import com.onlineBook.service.AuthorService;
import com.onlineBook.specification.AuthorSpecification;
import com.onlineBook.specification.SearchCriteria;

@Service
public class AuthorServiceImpl implements AuthorService{

  private final AuthorRepository authorRepository;

  public AuthorServiceImpl(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Override
  public void addAuthor(Author author) {  
    this.authorRepository.save(author);
  }

  @Override
  public List<Author> getAllAuthors() {
    return List.of();
    
  }

  @Override
  public void deleteAuthorById(int id) {
    this.authorRepository.deleteById(id);
  }

  @Override
  public Optional<Author> getAuthorById(int id) {
    return this.authorRepository.findByIdOrElseThrow(id);
  }

  @Override
  public Page<Author> getAllAuthors(Pageable pageable) {
    
    return this.authorRepository.findAll(pageable);
  }

  @Override
  public List<Author> search(String keyword) {
    return this.authorRepository.findAllByNameContainingOrSurnameContaining(keyword, keyword);
  }

  @Override
  public List<Author> filter(SearchCriteria criteria) {
    AuthorSpecification authorSpecification = new AuthorSpecification(criteria);
    return this.authorRepository.findAll(authorSpecification);
  }
  
}
