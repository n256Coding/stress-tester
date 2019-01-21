package com.n256coding.stresstester.controller;

import com.n256coding.stresstester.model.StressLevel;
import com.n256coding.stresstester.repository.Database;
import com.n256coding.stresstester.service.NLPProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class MainController {
    @Autowired
    Database database;

    @GetMapping("/depLevel")
    public HashMap<String, String> getRequest(String sentences) {
        HashMap<String, String> identifiedDepLevel = new HashMap<>();
        List<Double> severityList = new ArrayList<>();
//        HashSet<String> keywords = new HashSet<>(Arrays.asList(sentences.split(" ")));
        HashSet<String> keywords = new HashSet<>(NLPProcessor.getKeywords(sentences));

        for (String keyword : keywords) {
            StressLevel stressLevel = database.findStressLevelByKeywordsContains(keyword);
            if (stressLevel != null) {
                severityList.add(stressLevel.getSeverity());
            }
        }

        //If there is no targeted keyword found, return as unknown
        if (severityList.size() == 0) {
            identifiedDepLevel.put("dep_level", "unknown");
            return identifiedDepLevel;
        }

        Map<Double, Long> counts = severityList.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        Double maximumSeverity = Collections.max(counts.keySet());
        String depLevel = database.findBySeverityIs(maximumSeverity).getLevel();

        identifiedDepLevel.put("dep_level", depLevel);
        return identifiedDepLevel;
    }

}
