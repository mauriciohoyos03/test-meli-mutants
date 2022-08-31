package com.meli.test.mutants.service.impl;

import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import static java.lang.Math.min;
import static java.lang.Math.max;
import com.meli.test.mutants.dto.DataRequest;
import com.meli.test.mutants.entity.DnaSecuence;
import com.meli.test.mutants.repository.DnaSecuencesRepository;
import com.meli.test.mutants.service.MutantService;

@Service
public class MutantServiceImpl implements MutantService{

    private static final Logger logger = LoggerFactory.getLogger(MutantServiceImpl.class);
    private static final int NUMBER_TO_MUTANT = 4;
    private final String regexToValidate;
    private final DnaSecuencesRepository dnaRepository;

    public MutantServiceImpl(@Value("${regex.secuence.mutant}") final String regex, final DnaSecuencesRepository dRepository) {
        this.regexToValidate = regex;
        this.dnaRepository = dRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMutant(DataRequest data) {
        int n = data.getDna().length;
        String[][] dna = new String[n][n];
        logger.info("Regex {}", regexToValidate);
        final var uuid = UUID.nameUUIDFromBytes(Arrays.deepToString(data.getDna()).getBytes());
        logger.info("UUID IS: {}", uuid.toString());
        var dnaSecuence = this.dnaRepository.findDnaSecuenceByUUID(uuid.toString());
        if(dnaSecuence != null) {
            logger.info("Secuencia dna ya fue validada como {}", dnaSecuence.isMutant());
            return dnaSecuence.isMutant();
        }
        var isMutant = false;
        dnaSecuence = new DnaSecuence();
        dnaSecuence.setDna(data.getDna());
        dnaSecuence.setDnaUUID(uuid.toString());
        for (int i = 0; i < n; i++) {
            if(this.isMutantSecuence( data.getDna()[i])) {
                isMutant = true;
                dnaSecuence.setMutant(isMutant);
                this.dnaRepository.save(dnaSecuence);
                return isMutant;
            }
            dna[i] =  data.getDna()[i].split(""); 
        }
        isMutant = this.verticalValidation(dna) || this.oblicueValidation(dna);
        dnaSecuence.setMutant(isMutant);
        this.dnaRepository.save(dnaSecuence);
        return isMutant;
    }

    /**
     * Valida si una sequencia dna es mutante.
     * Aplica Regex con reglas de mutabilidad.
     * @param dnaSecuence {@link String}
     * @return {@link Boolean}
     */
    private boolean isMutantSecuence(String dnaSecuence) {
        if (dnaSecuence.length() >= NUMBER_TO_MUTANT && Pattern.compile(regexToValidate).matcher(dnaSecuence).find()) {
            logger.info("Secuencia dna mutante detectada {}", dnaSecuence);
            return true;
        }
        return false;
    }

    /**
     * Recorre la matriz de secuencia adn de forma oblicua de derecha a izq,
     * valida si encuentra una secuencia mutante.
     * @param dna 
     * @return {@link Boolean}
     */
    private boolean oblicueValidation(final String[][] dna) {
        int w = dna.length;
        int h = dna.length;
        for (int i = 1 - w; i < h; i++) {
           var line = "";
           for (int x = -min(0, i), y = max(0, i); x < w && y < h; x++, y++) {
                line = line.concat(dna[y][x]);
           }
           if (this.isMutantSecuence(line)) {
                return true;
           }
        }
        return false;
    }

    /**
     * Recorre la matriz de secuencia adn de forma vertical de izq a derecha,
     * valida si hay una secuencia mutante.
     * @param dna
     * @return {@link Boolean}
     */
    private boolean verticalValidation(final String[][] dna) {
        for (int i = 0; i < dna.length; i++) {
            var line = "";
            for (int j = 0; j < dna.length; j++) {
                line = line.concat(dna[j][i]);
            }
            if (this.isMutantSecuence(line)) {
                return true;
            }
        }
        return false;
    }
}
