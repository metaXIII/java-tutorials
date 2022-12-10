package com.metaxiii.fr.entitydtoconversionrestapi.controller;

import com.metaxiii.fr.entitydtoconversionrestapi.dto.PostDto;
import com.metaxiii.fr.entitydtoconversionrestapi.model.Post;
import com.metaxiii.fr.entitydtoconversionrestapi.service.PostService;
import com.metaxiii.fr.entitydtoconversionrestapi.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PostController {

  private final PostService postService;
  private final UserService userService;
  private final ModelMapper modelMapper;

  @GetMapping("{page}/{size}/{sortDir}/{sort}")
  @ResponseBody
  public List<PostDto> getPosts(
    @PathVariable("page") int page,
    @PathVariable("size") int size,
    @PathVariable("sortDir") String sortDir,
    @PathVariable("sort") String sort
  ) {
    List<Post> posts = postService.getPostsList(page, size, sortDir, sort);
    return posts.stream().map(this::toDTO).toList();
  }

  private PostDto toDTO(final Post post) {
    PostDto postDto = modelMapper.map(post, PostDto.class);
    postDto.setSubmissionDate(
      post.getSubmissionDate(),
      userService.getCurrentUser().getPreference().getTimezone()
    );
    return postDto;
  }
}
