package com.meli.test.mutants.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.test.mutants.dto.StatsResponse;
import com.meli.test.mutants.service.ExtrasService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ExtrasController {

    private final ExtrasService extrasService;

    @GetMapping("/stats")
    public StatsResponse getStats() {
        return this.extrasService.getStats();
    }
    
}
