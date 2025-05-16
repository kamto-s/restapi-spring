package com.coba.models.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.coba.models.entities.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {
  // search by name
  List<Product> findByNameContains(String name);
}
