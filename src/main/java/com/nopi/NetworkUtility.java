package com.nopi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public class NetworkUtility {

    public static String getPublicIp() throws IOException {
        String ip = "0.0.0.0";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://ipinfo.io/ip");

        HttpResponse response = httpClient.execute(request);
        HttpEntity httpEntity = response.getEntity();
        if( httpEntity != null){
            try (InputStream inputStream = httpEntity.getContent()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line = reader.readLine()) != null) {
                    ip = line;
                }
            }
        }
        return ip;
    }

    public static int sendPostWithJSON(String pendriveSerial, ControlData controlData) throws IOException{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://arminbet-backend.herokuapp.com/pendrive/" + pendriveSerial + "/addControl");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(controlData);
        StringEntity stringEntity = new StringEntity(json, "UTF-8");
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(httpPost);
        client.close();
        return response.getStatusLine().getStatusCode();
    }

    private static String readAll(Reader rd) throws IOException{
        StringBuilder sb = new StringBuilder();
        int cp;
        while((cp = rd.read()) != -1){
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJSONFromUrl(String url) throws IOException, JSONException{
        InputStream is = new URL(url).openStream();
        try{
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static String getPcLocation() throws IOException {
        String url = "http://ip-api.com/json";
        JSONObject json = readJSONFromUrl(url);
        return json.get("city").toString();
    }

    public static List<Object> getPendriveSerials() throws IOException {
        String url = "http://arminbet-backend.herokuapp.com/pendrive/pendriveSerials";
        JSONObject jsonObject = readJSONFromUrl(url);
        JSONArray jsonArray = (JSONArray) jsonObject.get("pendriveSerials");
        return jsonArray.toList();
    }

}
