package webapplication;


import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;





@Controller
public class ViewController {
    
	
    @GetMapping("/")
    
    public String index(){
        
    	return "index";
    }
}
