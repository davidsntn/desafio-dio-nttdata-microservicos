package com.nttdata.challenge.davidsantana.project.products_service.repository;

import com.nttdata.challenge.davidsantana.project.products_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContaining(String name);
    List<Product> findByPriceLessThanEqual(Double price);
}
