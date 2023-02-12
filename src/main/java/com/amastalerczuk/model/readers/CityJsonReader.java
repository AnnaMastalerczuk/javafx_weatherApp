package com.amastalerczuk.model.readers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class CityJsonReader {
    private Map<String, Integer> citiesMap = new TreeMap<>();

    public Map<String, Integer> getCitiesMapFromJSON(String source) throws FileNotFoundException {
        StringBuffer responseContent = new StringBuffer();
        try {
            File file = new File(source);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null){
//                System.out.println(line);
                responseContent.append(line);
            }
            citiesMap = parseCity(responseContent.toString());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return citiesMap;
    }

    public Map<String, Integer> parseCity(String responseBody) throws ParseException {
        Map<String, Integer> map = new TreeMap<>();
        JSONParser jsonParser = new JSONParser();
        // java obj
        Object javaObject = jsonParser.parse(responseBody);
        // convert to json obj
        JSONArray jsonArray = (JSONArray) javaObject;

        for (int i = 0 ; i < jsonArray.size(); i++){
            JSONObject object = (JSONObject) jsonArray.get(i);
            String name = (String) object.get("name");
            String country = (String) object.get("country");
            long cityId = (long) object.get("id");
            City city = new City(name, country, cityId);
            map.put(city.getCityName(), Math.toIntExact(city.getCityId()));
        }
        return map;
    }



}
