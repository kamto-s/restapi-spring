package com.coba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coba.models.entities.Product;
import com.coba.models.repos.ProductRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

  @Autowired
  private ProductRepo productRepo;

  // create product or update product
  public Product save(Product product) {
    return productRepo.save(product);
  }

  public Product findOne(Long id) {
    Optional<Product> product = productRepo.findById(id);

    if (!product.isPresent()) {
      return null;
    }

    return product.get();
  }

  public Iterable<Product> findAll() {
    return productRepo.findAll();
  }

  public void removeOne(Long id) {
    productRepo.deleteById(id);
  }

  // search
  public List<Product> findByName(String name) {
    return productRepo.findByNameContains(name);
  }
}
