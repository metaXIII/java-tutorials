package com.metaxiii.fr.hibernatereactive.service;

import com.metaxiii.fr.hibernatereactive.entity.Product;
import com.metaxiii.fr.hibernatereactive.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductService {

  private final ProductRepository repository;

  public Flux<Product> findAll() {
    return repository.findAll();
  }

  public Mono<Product> save(final Product product) {
    return repository.save(product);
  }
}
