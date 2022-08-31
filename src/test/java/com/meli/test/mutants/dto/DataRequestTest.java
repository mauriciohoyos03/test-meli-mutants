package com.meli.test.mutants.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataRequestTest {
    
    @Test
    void testGetterAndSetter() {
        final var request = new DataRequest();
        final var secuences = new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        request.setDna(secuences);
        Assertions.assertNotNull(request);
        Assertions.assertEquals(6, request.getDna().length);
    }
}
