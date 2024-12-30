package com.onlineBook.specification;

import com.onlineBook.entity.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
  private String name;
  private String surname;
  private String phone;
  private Gender gender;
}
