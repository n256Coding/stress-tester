package com.n256coding.stresstester.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.n256coding.stresstester.helper.ConfigReader;
import com.n256coding.stresstester.model.StressLevel;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.io.FileNotFoundException;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class Database {
    private static volatile Database database = new Database();
    MongoClientURI uri;
    MongoClient mongoClient;
    MongoTemplate mongoTemplate;

    private Database() {
        try {
            connect();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void connect() throws FileNotFoundException {
        this.uri = new MongoClientURI(ConfigReader.getConfig("connString"));
        this.mongoClient = new MongoClient(uri);
        this.mongoTemplate = new MongoTemplate(this.mongoClient, ConfigReader.getConfig("dbName"));
    }

    public static Database getInstance() {
        return database;
    }

    public String getDepLevel(String keyword){
        Query query = new Query(where("keywords").in(keyword));
        List<StressLevel> stressLevels = mongoTemplate.find(query, StressLevel.class);
        if(stressLevels.size() == 1){
            return stressLevels.get(0).getLevel();
        }else{
            return null;
        }
    }
}
