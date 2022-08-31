package com.meli.test.mutants.service.impl;

import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.meli.test.mutants.dto.StatsResponse;
import com.meli.test.mutants.repository.DnaSecuencesRepository;
import com.meli.test.mutants.service.ExtrasService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExtrasServiceImpl implements ExtrasService{

    private static final Logger logger = LoggerFactory.getLogger(ExtrasServiceImpl.class);
    private final DnaSecuencesRepository secuencesRepository;

    @Override
    public StatsResponse getStats() {
        // final var secuences = this.secuencesRepository.findAll();
        final var response = new StatsResponse();
        // if (!secuences.isEmpty()) {
        //     final long countMutants = secuences.stream().filter((t) -> t.isMutant()).count();
        //     final long countHumans = secuences.stream().filter(secuence -> !secuence.isMutant()).count();
        //     response.setCountMutantDna(countMutants);
        //     response.setCountHumanDna(countHumans);  
        //     
        // }
        var result = this.secuencesRepository.groupByIsMutant().getMappedResults();

        result.forEach(x -> {
            if(x.isMutant()){
                response.setCountMutantDna(x.getQuantity());
            } else {
                response.setCountHumanDna(x.getQuantity());
            };
        });
        response.setRatio();
        return response;
    }
}
