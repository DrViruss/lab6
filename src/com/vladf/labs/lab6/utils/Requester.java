package com.vladf.labs.lab6.utils;

import com.alibaba.fastjson.JSON;
import com.vladf.labs.lab6.data.PIS;
import com.vladf.labs.lab6.data.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Requester {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "http://api.open-notify.org/astros.json";
    private PIS responseData;

    public void sendRequest() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();

            this.responseData = JSON.parseObject(response.toString(),PIS.class);

           // System.out.println(t);            ADD WRITER
        }
        else {
            System.out.println("Something goes wrong...");
        }
    }

    public ArrayList<Person> getPeople() {
        return responseData.getPeople();
    }
}
