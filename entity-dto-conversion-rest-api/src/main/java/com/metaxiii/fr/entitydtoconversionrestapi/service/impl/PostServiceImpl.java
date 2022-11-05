package com.metaxiii.fr.entitydtoconversionrestapi.service.impl;

import com.metaxiii.fr.entitydtoconversionrestapi.model.Post;
import com.metaxiii.fr.entitydtoconversionrestapi.repository.PostRepository;
import com.metaxiii.fr.entitydtoconversionrestapi.service.PostService;
import com.metaxiii.fr.entitydtoconversionrestapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    @Override
    public List<Post> getPostsList(final int page, final int size, final String sortDir, final String sort) {
        PageRequest pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        Page<Post> posts = postRepository
                .findByUser(userService.getCurrentUser(), pageReq);
        return posts.getContent();
    }
}
