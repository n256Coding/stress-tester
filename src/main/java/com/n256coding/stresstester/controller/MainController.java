package com.n256coding.stresstester.controller;

import com.n256coding.stresstester.database.Database;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class MainController {

    @GetMapping("/depLevel")
    public void getRequest(String sentences) {
        HashSet<String> keywords = new HashSet<>(Arrays.asList(sentences.split(" ")));
        List<String> levels = new ArrayList<>();

        Database database = Database.getInstance();
        for (String keyword : keywords) {
            levels.add(database.getDepLevel(keyword));
        }

        Map<String, Long> counts = levels.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));

    }
}
