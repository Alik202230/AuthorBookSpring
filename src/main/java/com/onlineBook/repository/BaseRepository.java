package com.onlineBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
  default T findByIdOrElseThrow(ID id) {
    return findById(id).orElseThrow();
  }
}
