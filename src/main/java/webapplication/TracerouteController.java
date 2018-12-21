package webapplication;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TracerouteController {
//private static final String welcomemsg = "IP %s is reachable!";
    @GetMapping("WebApp/welcome/traceroute/ip")
    @ResponseBody
    public Welcome welcomeUser(@RequestParam(name="addr", required=true, defaultValue="") String addr) throws UnknownHostException {
    	String ip = addr;
        String tracerouteResult = "";
        String tracerouteCmd = "";
        
        InetAddress address = InetAddress.getByName(ip);
        
        if (address instanceof Inet6Address) {
        	tracerouteCmd = "traceroute6 " + ip;         } 
        
        else if (address instanceof Inet4Address) {
        	tracerouteCmd = "traceroute " + ip;    //send 4 packets
        }


        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(tracerouteCmd);

            BufferedReader in = new BufferedReader(new
            InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
            	
               // System.out.println(inputLine);
            	tracerouteResult += inputLine + "\r\n";
               //pingResult += inputLine + "<br/>";
                    
            }
            in.close();
            return new Welcome((tracerouteResult));
        } catch (IOException e) {
           // System.out.println(e);
        	return null;
        }
    	
    }
}

