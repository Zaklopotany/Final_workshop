package pl.coderslab.final_project.service;

public interface UserSubscriptionService {
	public boolean addUserSubscription(Long userId, Long subId);
	public boolean deleteUserSubscription(Long userId, Long subId);
}
