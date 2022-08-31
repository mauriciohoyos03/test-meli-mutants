package com.meli.test.mutants.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("dnasecuence")
public class DnaSecuence {
    
    @Id
    private String id;
    private String dnaUUID;
    private String[] dna;
    private boolean isMutant;
    
}
