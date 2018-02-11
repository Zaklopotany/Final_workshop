package pl.coderslab.final_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping({"/","/home"})
	public String getHome() {
		return "layout/index";
	}
	
	@RequestMapping({"/login"})
	public String getLogin() {
		return "login/login";
	}
	
	
}
