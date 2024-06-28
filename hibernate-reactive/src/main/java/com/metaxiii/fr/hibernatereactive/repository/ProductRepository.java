package com.metaxiii.fr.hibernatereactive.repository;

import com.metaxiii.fr.hibernatereactive.entity.Product;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends R2dbcRepository<Product, Long> {}
