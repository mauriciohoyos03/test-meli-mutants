package com.meli.test.mutants.controller;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.test.mutants.dto.DataRequest;
import com.meli.test.mutants.service.MutantService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MutantsController.class)
public class MutantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private MutantService mutantService;

    @Test
    void whenDnaSecuenceIsMutant() throws Exception {
        Mockito.when(this.mutantService.isMutant(ArgumentMatchers.any())).thenReturn(true);
        final var request = new DataRequest();
        final var secuences = new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        request.setDna(secuences);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/mutant/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(this.mutantService, times(1)).isMutant(ArgumentMatchers.any());
    }

    @Test
    void whenDnaSecuenceIsNotMutant() throws Exception {
        Mockito.when(this.mutantService.isMutant(ArgumentMatchers.any())).thenReturn(false);
        final var request = new DataRequest();
        final var secuences = new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CGCCTA","TCACTG"};
        request.setDna(secuences);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/mutant/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isForbidden());
        Mockito.verify(this.mutantService, times(1)).isMutant(ArgumentMatchers.any());
    }
    
}
