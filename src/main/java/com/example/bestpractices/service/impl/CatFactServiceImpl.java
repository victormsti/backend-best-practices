package com.example.bestpractices.service.impl;

import com.example.bestpractices.client.CatFactClient;
import com.example.bestpractices.client.response.CatFactResponse;
import com.example.bestpractices.service.contract.CatFactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatFactServiceImpl implements CatFactService {

    private final CatFactClient catFactClient;

    @Autowired
    public CatFactServiceImpl(CatFactClient catFactClient) {
        this.catFactClient = catFactClient;
    }

    @Override
    public CatFactResponse getCatFact() {
        return catFactClient.getCatFact();
    }
}
