package com.n256coding.stresstester.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "dep_level")
@AllArgsConstructor
public class StressLevel {


    @Getter @Setter
    private String level;

    @Getter @Setter
    private List<String> keywords;


}
