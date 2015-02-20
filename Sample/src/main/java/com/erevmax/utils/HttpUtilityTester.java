/*package com.erevmax.utils;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
 
public class HttpUtilityTester {
 
    *//**
     * This program uses the HttpUtility class to send a GET request to
     * Google home page; and send a POST request to Gmail login page.
     *//*
    public static void main(String[] args) {
        // test sending GET request
        String requestURL = "http://www.google.com";
        try {
            HttpUtility.sendGetRequest(requestURL);
            String[] response = HttpUtility.readMultipleLinesRespone();
            for (String line : response) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        HttpUtility.disconnect();
         
         
        System.out.println("=====================================");
         
        // test sending POST request
        Map<String, String> params = new HashMap<String, String>();
        requestURL = "https://accounts.google.com/ServiceLoginAuth";
        params.put("Email", "pathakshubham28@gmail.com");
        params.put("Passwd", "");
         
        try {
            HttpUtility.sendPostRequest(requestURL, params);
            String[] response = HttpUtility.readMultipleLinesRespone();
            for (String line : response) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        HttpUtility.disconnect();
    }
}*/