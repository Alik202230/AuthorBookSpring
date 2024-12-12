package com.onlineBook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "author")
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private String surname;
  private String phone;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dateOfBirthday;
  @Enumerated(EnumType.STRING)
  private Gender gender;

}
