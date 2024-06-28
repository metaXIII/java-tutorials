package com.metaxiii.fr.hibernatereactive.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.metaxiii.fr.hibernatereactive.configuration.R2DBCConfiguration;
import com.metaxiii.fr.hibernatereactive.entity.Product;
import com.metaxiii.fr.hibernatereactive.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest(classes = { R2DBCConfiguration.class, ProductService.class, ProductRepository.class })
class ProductServiceTest {

  @Autowired
  private ProductService productService;

  @Autowired
  private ProductRepository productRepository;

  @BeforeEach
  void beforeEach() {
    productRepository
      .deleteAll()
      .then(productRepository.save(new Product(1L, "Product 1", "Category 1", 10.0)))
      .then(productRepository.save(new Product(2L, "Product 2", "Category 2", 15.0)))
      .then(productRepository.save(new Product(3L, "Product 3", "Category 3", 20.0)))
      .block();
  }

  @Test
  void testFindAll() {
    StepVerifier.create(productService.findAll()).expectNextCount(3).verifyComplete();
  }

  @Test
  void testSave() {
    final Product newProduct = new Product(4L, "Product 4", "Category 4", 24.0);
    StepVerifier
      .create(productService.save(newProduct))
      .assertNext(product -> {
        assertNotNull(product.getId());
        assertEquals("Product 4", product.getName());
      })
      .verifyComplete();

    StepVerifier.create(productService.findAll()).expectNextCount(4).verifyComplete();
  }
}
