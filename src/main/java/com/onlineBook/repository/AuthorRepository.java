package com.onlineBook.repository;

import com.onlineBook.entity.Author;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AuthorRepository extends BaseRepository<Author, Integer>, JpaSpecificationExecutor<Author> {
  List<Author> findAllByNameContainingOrSurnameContaining(String name, String surname);
}
