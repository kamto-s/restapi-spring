package com.coba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coba.dto.ResponseProduct;
import com.coba.models.entities.Product;
import com.coba.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  // create product
  @PostMapping
  public ResponseEntity<ResponseProduct<Product>> create(@Valid @RequestBody Product product, Errors errors) {

    ResponseProduct<Product> responseProduct = new ResponseProduct<>();

    if (errors.hasErrors()) {
      for (ObjectError err : errors.getAllErrors()) {
        responseProduct.getMessages().add(err.getDefaultMessage());
      }
      responseProduct.setStatus(false);
      responseProduct.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseProduct);
    }

    responseProduct.setStatus(true);
    responseProduct.setPayload(productService.save(product));

    return ResponseEntity.ok(responseProduct);
  }

  // get all products
  @GetMapping
  public Iterable<Product> findAll() {
    return productService.findAll();
  }

  // get product by id
  @GetMapping("/{id}")
  public Product findOne(@PathVariable("id") Long id) {
    return productService.findOne(id);
  }

  // update product
  @PutMapping("/{id}")
  public ResponseEntity<ResponseProduct<Product>> update(@Valid @RequestBody Product product, Errors errors) {
    ResponseProduct<Product> responseProduct = new ResponseProduct<>();

    if (errors.hasErrors()) {
      for (ObjectError err : errors.getAllErrors()) {
        responseProduct.getMessages().add(err.getDefaultMessage());
      }
      responseProduct.setStatus(false);
      responseProduct.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseProduct);
    }

    responseProduct.setStatus(true);
    responseProduct.setPayload(productService.save(product));

    return ResponseEntity.ok(responseProduct);
  }

  // delete product
  @DeleteMapping("/{id}")
  public void removeOne(@PathVariable("id") Long id) {
    productService.removeOne(id);
  }

  // search product by name
  @GetMapping("/search/{name}")
  public Iterable<Product> findByName(@PathVariable String name) {
    return productService.findByName(name);
  }

}
