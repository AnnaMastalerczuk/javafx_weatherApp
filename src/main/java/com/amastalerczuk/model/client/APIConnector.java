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


//    public APIConnector(String urlString) throws MalformedURLException {
//        this.urlString = urlString;
//    }
    public APIConnector() {
    }

    public String fetchDataFromAPI(String urlString){
        this.urlString = urlString;
//        System.out.println(urlString);
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
//            System.out.println(responseContent.toString());
//            parse(responseContent.toString());

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

//    public JSONArray getJSONArray(){
//        try {
//            URL url = new URL(urlString);
//
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.connect();
//
//            //Check if connect is made
//            int responseCode = conn.getResponseCode();
//
//            if (responseCode != 200) {
//                throw new RuntimeException("HttpResponseCode: " + responseCode);
//            } else {
//
//                StringBuilder informationString = new StringBuilder();
//                Scanner scanner = new Scanner(url.openStream());
//
//                while (scanner.hasNext()) {
//                    informationString.append(scanner.nextLine());
//                }
//                scanner.close();
//
//                JSONParser parse = new JSONParser();
//
//                return (JSONArray) parse.parse(String.valueOf(informationString));
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public JSONObject getJSONObject(){
//        try {
//            URL url = new URL(urlString);
//
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.connect();
//
//            //Check if connect is made
//            int responseCode = conn.getResponseCode();
//
//            if (responseCode != 200) {
//                throw new RuntimeException("HttpResponseCode: " + responseCode);
//            } else {
//
//                StringBuilder informationString = new StringBuilder();
//                Scanner scanner = new Scanner(url.openStream());
//
//                while (scanner.hasNext()) {
//                    informationString.append(scanner.nextLine());
//                }
//                scanner.close();
//
//                JSONParser parse = new JSONParser();
////                System.out.println(informationString);
//
//                return (JSONObject) parse.parse(String.valueOf(informationString));
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
