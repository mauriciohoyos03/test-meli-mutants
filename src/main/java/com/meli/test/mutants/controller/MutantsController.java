package com.meli.test.mutants.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.test.mutants.dto.DataRequest;
import com.meli.test.mutants.service.MutantService;

import lombok.RequiredArgsConstructor;

@RestController()
@RequiredArgsConstructor
public class MutantsController {

    private final MutantService mutantService;
    
    @PostMapping(value = "/mutant/")
    public ResponseEntity<Void> isMutant(@Valid @RequestBody DataRequest body) {
        if (this.mutantService.isMutant(body)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    } 
}
