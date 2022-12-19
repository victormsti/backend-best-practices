package com.example.bestpractices.client;

import com.example.bestpractices.client.response.CatFactResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "CatFactClient", url = "${feign.cat-fact.url}")
public interface CatFactClient {

    @GetMapping("fact")
    CatFactResponse getCatFact();
}
