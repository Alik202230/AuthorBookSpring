package com.onlineBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.onlineBook.entity.Author;

public interface AuthorRepository extends BaseRepository<Author, Integer>, JpaSpecificationExecutor<Author> {
  List<Author> findAllByNameContainingOrSurnameContaining(String name, String surname);
}
