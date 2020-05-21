package com.alok.springweb.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alok.springweb.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
