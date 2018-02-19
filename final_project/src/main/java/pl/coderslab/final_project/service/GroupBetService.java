package pl.coderslab.final_project.service;

import pl.coderslab.final_project.entity.bet.GroupBet;

public interface GroupBetService {

	public void createNewGroupBet(Long groupMatchId) throws Exception;
	
	
	/**
	 * this method will add group of users to groupBet
	 * if they accept invitation the UserBet will appear in group bet
	 * 
	 */
	
	public void inviteUsersToGroupBet(GroupBet groupBet) throws Exception;

	
}
