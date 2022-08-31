package com.meli.test.mutants.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meli.test.mutants.dto.DataRequest;
import com.meli.test.mutants.repository.DnaSecuencesRepository;
import com.meli.test.mutants.service.impl.MutantServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MutantServiceTest {

    @Mock
    private DnaSecuencesRepository dRepository;
    private MutantService mutantService;

    @BeforeEach
    public void init() {
        this.mutantService = new MutantServiceImpl("A{4}|T{4}|C{4}|G{4}", dRepository);
        Mockito.when(this.dRepository.findDnaSecuenceByUUID(ArgumentMatchers.anyString())).thenReturn(null);
    }
    
    @Test
    void whenDnaSecuencesIsMutant() {
        final var request = new DataRequest();
        final var secuences = new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        request.setDna(secuences);
        final var result = this.mutantService.isMutant(request);
        Assertions.assertTrue(result);
    }

    @Test
    void whenDnaSecuencesIsMutantVertical() {
        final var request = new DataRequest();
        final var secuences = new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCGCTA","TCACTG"};
        request.setDna(secuences);
        final var result = this.mutantService.isMutant(request);
        Assertions.assertTrue(result);
    }

    @Test
    void whenDnaSecuencesIsMutantOblicue() {
        final var request = new DataRequest();
        final var secuences = new String[]{"ATGCTA","CAGTGC","TTATGT","AGAAGG","CCTCTA","TCACTG"};
        request.setDna(secuences);
        final var result = this.mutantService.isMutant(request);
        Assertions.assertTrue(result);
    }
    
}
