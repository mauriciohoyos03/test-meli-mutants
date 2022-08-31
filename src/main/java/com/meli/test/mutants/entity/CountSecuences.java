package com.meli.test.mutants.entity;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CountSecuences {

    @Id
    private boolean isMutant;
    private int quantity;
    
}
