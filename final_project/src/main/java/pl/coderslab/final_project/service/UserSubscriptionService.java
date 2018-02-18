package pl.coderslab.final_project.service;

public interface UserSubscriptionService {
	/**
	 * add new {@link User} to Subscription list
	 * @param subId
	 * @return true if operation succeed otherwise false
	 */
	public boolean addUserSubscription(Long subId);
	public boolean deleteUserSubscription(Long subId);
	public void sendSubscription();
}
