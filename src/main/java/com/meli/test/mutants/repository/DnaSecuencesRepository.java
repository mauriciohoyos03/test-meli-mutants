package com.meli.test.mutants.repository;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.meli.test.mutants.entity.CountSecuences;
import com.meli.test.mutants.entity.DnaSecuence;

@Repository
public interface DnaSecuencesRepository extends MongoRepository<DnaSecuence, String> {

    @Query("{dnaUUID:'?0'}")
    DnaSecuence findDnaSecuenceByUUID(String uuid);

    @Aggregation( pipeline = {"{$group: {'_id':'$isMutant', 'quantity':{$count: {}}}}"})
    AggregationResults<CountSecuences> groupByIsMutant();

}
