package com.metaxiii.fr.springdatagetlastrecord.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post {

  @Id
  private Long id;

  private String title;
  private LocalDate publicationDate;
}
