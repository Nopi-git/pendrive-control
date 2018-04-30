package com.nopi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
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

    public static int sendPost(Control control) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://arminbet-pendrive.herokuapp.com/controldata");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("armId", control.getArmId().toString()));
        params.add(new BasicNameValuePair("description", control.getDescription()));
        params.add(new BasicNameValuePair("publicIp", control.getPublicIp()));
        params.add(new BasicNameValuePair("pcMotherBoardSerialNumber", control.getMotherBoardSerial()));
        params.add(new BasicNameValuePair("pendriveSerial", control.getPendriveSerial()));
        params.add(new BasicNameValuePair("macAddress", control.getMacAddress()));
        params.add(new BasicNameValuePair("location", control.getLocation()));
        params.add(new BasicNameValuePair("date", control.getDate().toString()));
        params.add(new BasicNameValuePair("lastBootUpTime", control.getLastBootUpTime()));

        httpPost.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = client.execute(httpPost);
        client.close();
        return response.getStatusLine().getStatusCode();
    }

    static String readAll(Reader rd) throws IOException{
        StringBuilder sb = new StringBuilder();
        int cp;
        while((cp = rd.read()) != -1){
            sb.append((char) cp);
        }
        return sb.toString();
    }

    static JSONObject readJSONFromUrl(String url) throws IOException, JSONException{
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
}
