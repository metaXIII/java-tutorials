package com.metaxiii.fr.entitydtoconversionrestapi.service;

import com.metaxiii.fr.entitydtoconversionrestapi.model.Post;
import java.util.List;

public interface PostService {
  List<Post> getPostsList(int page, int size, String sortDir, String sort);
}
