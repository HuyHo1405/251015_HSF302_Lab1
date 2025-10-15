package com.example.demo.repo;

import com.example.demo.model.entity.SonyAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<SonyAccount, Integer> {
    SonyAccount findByPhoneAndPassword(String phone, String password);
}
