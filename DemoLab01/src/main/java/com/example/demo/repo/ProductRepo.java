package com.example.demo.repo;

import com.example.demo.model.entity.SonyProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<SonyProduct, Long> {
}
