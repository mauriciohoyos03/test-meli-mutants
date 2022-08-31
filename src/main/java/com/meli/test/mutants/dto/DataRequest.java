package com.meli.test.mutants.dto;

import javax.validation.constraints.NotNull;

import com.meli.test.mutants.validations.DnaValId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataRequest {

    @NotNull(message = "Secuencia dna requerida")
    @DnaValId(pattern = "[^ATCG]")
    private String[] dna;

}
