package com.example.demo.service;

import com.example.demo.model.entity.SonyAccount;
import com.example.demo.model.entity.SonyCategory;
import com.example.demo.model.entity.SonyProduct;
import com.example.demo.repo.AccountRepo;
import com.example.demo.repo.CateRepo;
import com.example.demo.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SonyServiceImpl implements SonyService {
    private final AccountRepo accountRepo;
    private final ProductRepo productRepo;
    private final CateRepo cateRepo;

    @Override
    public SonyAccount login(String phone, String pw) {
        return accountRepo.findByPhoneAndPassword(phone, pw);
    }

    @Override
    public void logout(String token) {
    }

    @Override
    public List<SonyProduct> getAllProducts() {
        return productRepo.findAll();
    }

    public List<SonyCategory> getAllCategories() {
        return cateRepo.findAll();
    }

    @Override
    public SonyProduct getByProductId(long productId) {
        return productRepo.findById(productId).orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        if(productRepo.existsById(id)) productRepo.deleteById(id);
    }

    @Override
    public SonyProduct save(SonyProduct product) {
        if(product.getProductId() != 0) {
            // UPDATE - giữ nguyên createAt cũ
            SonyProduct existingProduct = productRepo.findById(product.getProductId()).orElse(null);
            if(existingProduct == null) return null;

            product.setCreateAt(existingProduct.getCreateAt());
        } else {
            // ADD - set createAt mới
            product.setCreateAt(LocalDate.now());
        }

        return productRepo.save(product);
    }
}
