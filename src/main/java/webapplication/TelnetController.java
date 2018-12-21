package webapplication;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TelnetController {
//private static final String welcomemsg = "IP %s is reachable!";
  @GetMapping("WebApp/welcome/telnet/ip")
  
 
  @ResponseBody
  public Welcome welcomeUser(@RequestParam String addr,@RequestParam int pr)  {
	 
	  String ip = addr;
	  int port = pr;
      String telnetResult = "";
      
      
          Socket s = null;
          try
          {
              s = new Socket(ip, port);
              telnetResult = "Connected to IP " + ip + " on port " + port + "!";
              return new Welcome((telnetResult));
          }
          catch (Exception e)
          {
              telnetResult = "Connection refused!" + "\r\n" + "Telnet: Unable to connect to remote host on port " + port;
               return new Welcome((telnetResult));

          }
          finally
          {
              if(s != null)
                  try {s.close();}
                  catch(Exception e){}
          }
  }
}








      