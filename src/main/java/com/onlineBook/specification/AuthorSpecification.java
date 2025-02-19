package com.onlineBook.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.onlineBook.entity.Author;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class AuthorSpecification implements Specification<Author> {

  private final transient SearchCriteria criteria;

  public AuthorSpecification (SearchCriteria criteria) {
    this.criteria = criteria;
  }

  @Override
  public Predicate toPredicate(@NonNull Root<Author> root, @Nullable CriteriaQuery<?> query,
      @NonNull CriteriaBuilder criteriaBuilder) {
    List<Predicate> predicats = new ArrayList<>();
    if (criteria.getName() != null && !criteria.getName().isEmpty()) {
      predicats.add(criteriaBuilder.like(root.get("name"), "%" + criteria.getName() + "%"));
    }
    if (criteria.getSurname() != null && !criteria.getSurname().isEmpty()) {
      predicats.add(criteriaBuilder.like(root.get("surname"), "%" + criteria.getSurname() + "%"));
    }
    if (criteria.getPhone() != null && !criteria.getPhone().isEmpty()) {
      predicats.add(criteriaBuilder.like(root.get("phone"), "%" + criteria.getPhone() + "%"));
    }
    if (criteria.getGender() != null) {
      predicats.add(criteriaBuilder.like(root.get("gender"), "%" + criteria.getGender().name() + "%"));
    }
    if (query == null) {
      throw new IllegalStateException("Query must be initialized before building the restriction");
    }
    return query.where(criteriaBuilder.and(predicats.toArray(new Predicate[0]))).getRestriction();
  }

}
