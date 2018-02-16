package pl.coderslab.final_project.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.final_project.entity.user.User;
import pl.coderslab.final_project.entity.user.UserSubscription;
import pl.coderslab.final_project.repository.UserRepository;
import pl.coderslab.final_project.repository.UserSubcriptionRepository;
import pl.coderslab.final_project.service.CurrentUser;
import pl.coderslab.final_project.utils.Email.EmailServiceImpl;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {
	@Autowired
	private UserSubcriptionRepository userSubs;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private EmailServiceImpl emailService;
	
	@GetMapping("/user")
	public String getUserSubscription(Model model) {
		List<UserSubscription> subs = new ArrayList<>();
		CurrentUser cUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepo.findOne(cUser.getUserId()); 
		Set<User> hashSet = new HashSet<>();
		hashSet.add(user);
		model.addAttribute("list",userSubs.findByUsersIn(hashSet));
		return "";
	}
	
	@GetMapping("/all")
	public String getAllSubs(Model model) {
		model.addAttribute("subs", userSubs.findAll());
		return "";
	}
	
//	@PostMapping("/add/{id}")
//	public String addNewSub(@PathVariable("id") Long id, Model model) {
//		model.addAttribute("subs", );
//		return "";
//	}
	
	@GetMapping("/send")
	public void sendEmail() {
		emailService.sendSimpleMessage("finalspringproject@gmail.com", "asd", "asd");
	}
	
}
