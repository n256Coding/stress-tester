package com.n256coding.stresstester.helper;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConfigReader {
    public static String getConfig(String property) throws FileNotFoundException {
        JsonReader jsonFile = new JsonReader(new FileReader("config.json"));
        JsonObject jsonObject = new JsonParser().parse(jsonFile).getAsJsonObject();
        return jsonObject.get(property).getAsString();
    }
}
