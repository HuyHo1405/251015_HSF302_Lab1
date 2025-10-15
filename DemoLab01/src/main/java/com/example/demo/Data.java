package com.example.demo;

import com.example.demo.model.entity.SonyAccount;
import com.example.demo.model.entity.SonyCategory;
import com.example.demo.model.entity.SonyProduct;
import com.example.demo.repo.AccountRepo;
import com.example.demo.repo.CateRepo;
import com.example.demo.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class Data implements CommandLineRunner {
    private final ProductRepo productRepo;
    private final AccountRepo accountRepo;
    private final CateRepo cateRepo;

    @Override
    public void run(String... args) throws Exception {
        if(accountRepo.count() != 0){
            return;
        }
        accountRepo.saveAll(List.of(
                new SonyAccount("0905111111",	"@1",	1),
                new SonyAccount("0905222222",	"@1",	2),
                new SonyAccount("0905333333",	"@1",	3)
        ));

        SonyCategory c1 = cateRepo.save(new SonyCategory("HeadPhone"	,"active"));
        SonyCategory c2 = cateRepo.save(new SonyCategory("Cameras"	,"active"));
        SonyCategory c3 = cateRepo.save(new SonyCategory("TVs"	,"active"));

        productRepo.saveAll(List.of(
                new SonyProduct("Alpha 1 II - Full-frame Mirrorless",	6000,	3	, LocalDate.parse("2025-03-03") ,	c2),
                new SonyProduct("Alpha 7C II – Full-frame",	2000,	5	,LocalDate.parse("2025-04-04") ,	c2),
                new SonyProduct("BRAVIA 8 OLED 4K HDR TV",	2500,	10	,LocalDate.parse("2025-01-01") ,	c3),
                new SonyProduct("LinkBuds Fit Truly Wireless Noise Canceling",	180,	15	,LocalDate.parse("2025-03-03") ,	c1)
        ));



    }

}
