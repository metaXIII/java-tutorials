package com.metaxiii.fr.entitydtoconversionrestapi.repository;

import com.metaxiii.fr.entitydtoconversionrestapi.model.Post;
import com.metaxiii.fr.entitydtoconversionrestapi.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
  @Query("select p from Post p where p.userName=:userName")
  Page<Post> findByUser(@Param("userName") String userName, Pageable pageReq);

  default Page<Post> findByUser(User user, Pageable pageReq) {
    return findByUser(user.getName(), pageReq);
  }
}
