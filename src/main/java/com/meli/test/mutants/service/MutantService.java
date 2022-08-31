package com.meli.test.mutants.service;

import com.meli.test.mutants.dto.DataRequest;

public interface MutantService {

    /**
     * Valida si una secuencia adn es mutante.
     * @param data {@link DataRequest}
     * @return {@link Boolean} 
     */
    boolean isMutant(DataRequest data);
}
