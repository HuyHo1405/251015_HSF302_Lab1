package com.example.demo;

import com.example.demo.model.entity.SonyAccount;
import com.example.demo.repo.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SonyServiceImpl implements SonyService{
    private final AccountRepo accountRepo;

    @Override
    public SonyAccount login(String phone, String pw) {
        return accountRepo.findByPhoneAndPassword(phone, pw);
    }

    @Override
    public void logout(String token) {
    }
}
