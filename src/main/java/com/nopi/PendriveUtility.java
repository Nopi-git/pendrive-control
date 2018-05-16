package com.nopi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PendriveUtility {
    private List<String> serialNumbers = new ArrayList<String>();


    public PendriveUtility() throws IOException{
        fillListWithUSBSerials();
    }

    private void fillListWithUSBSerials() throws IOException{
        String line;
        int counter = 0;
        Process process = Runtime.getRuntime().exec("wmic diskdrive get serialnumber");
        process.getOutputStream().close();
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while((line = input.readLine())!=null){
            if(++counter%2==0) continue;
            String serial = line.replaceAll("\\s+", "");
            this.serialNumbers.add(serial);
        }
        input.close();
        this.serialNumbers.remove(0);
        this.serialNumbers.remove(this.serialNumbers.size()-1);
    }
    public boolean serialInList(String serial) throws NoPendriveException {
        if(this.serialNumbers.contains(serial)) return true;
        else throw new NoPendriveException("No valid pendrive in PC");
    }
}
