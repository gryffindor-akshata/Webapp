package webapplication;


//package com.baithi.rest.controller;
//import com.baithi.beans.Welcome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class MTRController {
//private static final String welcomemsg = "IP %s is reachable!";
	
    @GetMapping("WebApp/welcome/mtr/ip")
    @ResponseBody
    public Welcome welcomeUser(@RequestParam(name="addr", required=true, defaultValue="") String addr) {
    	String ip = addr;
        String mtrResult = "";
        

        String mtrCmd = "mtr " + ip;      

        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(mtrCmd);

            BufferedReader in = new BufferedReader(new
            InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
            	
               // System.out.println(inputLine);
                mtrResult += inputLine + "\r\n";
               
                    
            }
            in.close();
            return new Welcome((mtrResult));
        } catch (IOException e) {
           // System.out.println(e);
        	return null;
        }
    	
    }
}

/*class pingTest {

    public static void main(String[] args) {
        String ip = addr;
        String pingResult = "";

        String pingCmd = "ping " + ip;
        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);

            BufferedReader in = new BufferedReader(new
            InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
               // System.out.println(inputLine);
                pingResult += inputLine;
            }
            in.close();

        } catch (IOException e) {
            System.out.println(e);
        }

    }
}*/