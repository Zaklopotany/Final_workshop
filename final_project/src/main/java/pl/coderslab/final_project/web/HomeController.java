package pl.coderslab.final_project.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.final_project.entity.user.User;
import pl.coderslab.final_project.repository.UserRepository;
import pl.coderslab.final_project.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping({"/","/home"})
	public String getHome() {
		return "layout/index";
	}
	
	@RequestMapping({"/login"})
	public String getLogin() {
		return "login/login";
	}
	@RequestMapping({"/register"})
	public String getRegister(Model model) {
		model.addAttribute("user", new User());
		return "login/register";
	}
	
	@PostMapping({"/register"})
	public String saveNewUser(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return "login/register";
		}
		userService.saveUser(user);
		return "login/login";

	}
	
}
