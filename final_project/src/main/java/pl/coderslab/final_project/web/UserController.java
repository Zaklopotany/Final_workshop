package pl.coderslab.final_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	
	
	
	@RequestMapping("/home")
	public String getUserHome() {		
		return "user/text";
	}
	
	
	
	
}
