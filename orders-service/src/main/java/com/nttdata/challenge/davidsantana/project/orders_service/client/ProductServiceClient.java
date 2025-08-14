package com.nttdata.challenge.davidsantana.project.orders_service.client;

import com.nttdata.challenge.davidsantana.project.orders_service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products-service")
public interface ProductServiceClient {

    @GetMapping("/api/products/{id}")
    ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id);
}
