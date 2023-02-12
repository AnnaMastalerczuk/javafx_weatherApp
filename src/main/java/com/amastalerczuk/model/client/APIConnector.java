package com.amastalerczuk.model.client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

public class APIConnector {
    private String urlString;
    private BufferedReader reader;
    private String line;
    private StringBuffer responseContent = new StringBuffer();
    private HttpURLConnection connection;


    public APIConnector() {
    }

    public String fetchDataFromAPI(String urlString){
        this.urlString = urlString;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();
            if (status != 200){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return responseContent.toString();
    }

    public String parse(String responseBody) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        // java obj
        Object obj = jsonParser.parse(responseBody);
        // convert to json obj
        JSONObject empjsonobj = (JSONObject) obj;
        // extract datas
        JSONObject obj2 = (JSONObject) empjsonobj.get("current");
        String name = (String) obj2.get("last_updated");
        double temp = (double) obj2.get("temp_c");
        System.out.println(name);
        System.out.println(temp);
        return null;
    }

}
