package com.example.demo.repo;

import com.example.demo.model.entity.SonyCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CateRepo extends JpaRepository<SonyCategory, Integer> {
}
