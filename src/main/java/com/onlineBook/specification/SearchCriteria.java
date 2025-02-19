package com.onlineBook.specification;

import com.onlineBook.entity.Gender;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
  public String name;
  public String surname;
  public String phone;
  public Gender gender;
}
