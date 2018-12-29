package com.n256coding.stresstester.repository;

import com.n256coding.stresstester.model.StressLevel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Database extends MongoRepository<StressLevel, String> {
    public StressLevel findStressLevelByKeywordsContains(String keyword);
    public StressLevel findBySeverityIs(double severity);
}
