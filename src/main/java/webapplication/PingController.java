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
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.*;



@Controller
public class PingController {
//private static final String welcomemsg = "IP %s is reachable!";
	
    @GetMapping("WebApp/welcome/ping/ip")
    @ResponseBody
    public Welcome welcomeUser(@RequestParam(name="addr", required=true, defaultValue="") String addr) throws UnknownHostException {
    	String ip = addr;
        String pingResult = "";
        String pingCmd = "";
        
        InetAddress address = InetAddress.getByName(ip);
        
        if (address instanceof Inet6Address) {
             pingCmd = "ping6 " + "-I " + "en0 " + "-c " + "4 " + ip;
      	  //pingCmd = "ping6 " + ip;

        } 
        else if (address instanceof Inet4Address) {
              pingCmd = "ping " +  "-c " + "4 " + ip;    //send 4 packets
        }

          

        try {
        	
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);

            BufferedReader in = new BufferedReader(new
            InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
            	
               // System.out.println(inputLine);
                pingResult += inputLine + "\r\n";
               
                    
            }
            in.close();
            
            
            return new Welcome((pingResult));
        } catch (IOException e) {
           // System.out.println(e);
        	return null;
        }
    	
    }
}

