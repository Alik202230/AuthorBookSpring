package com.onlineBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
  default Optional<T> findByIdOrElseThrow(ID id) {
    return findById(id);
  }
}
