package pl.coderslab.final_project.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import pl.coderslab.final_project.entity.user.SubscriptionContent;
import pl.coderslab.final_project.entity.user.User;
import pl.coderslab.final_project.entity.user.UserSubscription;
import pl.coderslab.final_project.repository.SubscriptionContentRepository;
import pl.coderslab.final_project.repository.UserRepository;
import pl.coderslab.final_project.repository.UserSubcriptionRepository;
import pl.coderslab.final_project.utils.Email.EmailServiceImpl;

@Service
public class UserSubscriptionServiceImpl implements UserSubscriptionService{
	@Autowired
	UserSubcriptionRepository userSubsRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	SubscriptionContentRepository subscriptionConRepo;
	@Autowired
	private EmailServiceImpl emailService;
	
	@Override
	public boolean addUserSubscription(Long subId) {
		Set<User> users = new HashSet<>();
		CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userDb = userRepo.findOne(user.getUserId());
		users.add(userDb);
		UserSubscription userSubs= userSubsRepo.findOne(subId);
		List<UserSubscription> subsUser = userSubsRepo.findByUsersIn(users); 
		for(UserSubscription subs: subsUser) {
			if(subs.getId() == subId) {
				return false;
			}
		}
		Set<User> tempSet = userSubs.getUsers();
		tempSet.add(userDb);
		userSubs.setUsers(tempSet);
		userSubsRepo.save(userSubs);
		return true;
	}

	@Override
	public boolean deleteUserSubscription(Long subId) {
		Set<User> users = new HashSet<>();
		CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userDb = userRepo.findOne(user.getUserId());
		users.add(userDb);
		
		List<UserSubscription> subsUser = userSubsRepo.findByUsersIn(users); 
		
		for(UserSubscription subs: subsUser) {
			if(subs.getId() == subId) {
				Set<User> tempSet = new HashSet<>();
				tempSet = subs.getUsers();
				tempSet.remove(userDb);
				subs.setUsers(tempSet);
				userSubsRepo.save(subs);
				return true;
			}
		}
		return false;
	}

	@Override
	public void sendSubscription() {
		//TODO implement thymeleaf email template to send html emails
		List<SubscriptionContent> subList = subscriptionConRepo.findAllBySent(false);
		for(SubscriptionContent subs : subList) {
			UserSubscription userSub = subs.getSubscription();
			Set<User> userSet = userSubsRepo.getAllUsersForSub(userSub.getId());
			for(User user : userSet) {
				//mockup sending user emails 
				System.out.println("email to user : " + user.getEmail() + " sent");
				//emailService.sendSimpleMessage(user.getEmail(), userSub.getName(), "content");
			}
			subs.setSent(true);
			subscriptionConRepo.save(subs);
		}
		
	}


}
