package com.meli.test.mutants.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StatsResponse {

    private long countMutantDna;
    private long countHumanDna;
    private double ratio;

    public void setRatio() {
        if (this.countHumanDna == 0) {
            this.ratio = (100 * this.countMutantDna) / 100;
        } else {
            this.ratio = ((100 * this.countMutantDna) / this.countHumanDna) / 100;
        }
    }
    
}
