package com.example.bestpractices.controller;

import com.example.bestpractices.client.response.CatFactResponse;
import com.example.bestpractices.service.contract.CatFactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cats")
@Slf4j
public class CatFactController {

    private final CatFactService catFactService;

    @Autowired
    public CatFactController(CatFactService catFactService) {
        this.catFactService = catFactService;
    }

    @GetMapping("/facts")
    public ResponseEntity<CatFactResponse> getCatFact() {
        return ResponseEntity.ok().body(catFactService.getCatFact());
    }
}
