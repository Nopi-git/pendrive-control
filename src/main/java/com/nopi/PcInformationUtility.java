package com.nopi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PcInformationUtility {


    public static String getPcModel() throws IOException {
        String line;
        List<String> model = new ArrayList<>();
        Process process = Runtime.getRuntime().exec("wmic baseboard get serialnumber");
        process.getOutputStream().close();
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while((line = input.readLine())!=null){
            model.add(line.replaceAll("\\s+", ""));
        }
        input.close();
        return model.get(2);
    }

    public static String getPCMacAddress() throws IOException {
        String line;
        List<String> model = new ArrayList<>();
        Process process = Runtime.getRuntime().exec("wmic nic where (AdapterTypeId=0 AND netConnectionStatus=2) get MACAddress");
        process.getOutputStream().close();
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while((line = input.readLine())!=null){
            model.add(line.replaceAll("\\s+", ""));
        }
        input.close();
        return model.get(2);
    }

    public static String getLastBootUpTime() throws IOException{
        String line;
        List<String> model = new ArrayList<>();
        Process process = Runtime.getRuntime().exec("wmic os get lastbootuptime");
        process.getOutputStream().close();
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while((line = input.readLine())!=null){
            model.add(line.replaceAll("\\s+", ""));
        }
        input.close();
        String lastBootUpTime = model.get(2).substring(0, 4) + '.' + model.get(2).substring(4, 6) + '.' + model.get(2).substring(6, 8);
        return lastBootUpTime;
    }
}
