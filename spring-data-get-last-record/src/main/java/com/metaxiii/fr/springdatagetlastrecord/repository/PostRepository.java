package com.metaxiii.fr.springdatagetlastrecord.repository;

import com.metaxiii.fr.springdatagetlastrecord.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  Post findFirstByOrderByPublicationDateDesc();

  Post findTopByOrderByPublicationDateDesc();

  @Query(value = "select p from Post p order by p.publicationDate desc limit 1")
  Post findLastPost();
}
