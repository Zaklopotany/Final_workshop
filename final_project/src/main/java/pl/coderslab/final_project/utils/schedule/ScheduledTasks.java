package pl.coderslab.final_project.utils.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.coderslab.final_project.service.UserSubscriptionService;
/**
 * this class will contain methods that will check bets and other parameters to keep them up to date
 * also send emails with subscription
 * @author zaklopotany
 *
 */
@Component
public class ScheduledTasks {
	
	@Autowired
	UserSubscriptionService userSubServ;
	
	@Scheduled(cron = "0 0 */8 ? * *")
	public void sendSubscription() {
		userSubServ.sendSubscription();
	}
	
}
