package com.onlineBook.repository;

import com.onlineBook.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

  default Author findByIdOrThrow(int id) {
    return findById(id).orElseThrow();
  }

}
