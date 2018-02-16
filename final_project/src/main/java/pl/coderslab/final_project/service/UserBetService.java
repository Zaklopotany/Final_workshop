package pl.coderslab.final_project.service;

import java.util.List;

import pl.coderslab.final_project.entity.bet.UserBet;

public interface UserBetService {
	
	UserBet addBetToUserBasket(Long userId, Long betId) throws Exception;
	
	List<UserBet> getUserBasket(Long userId);
	
	boolean validateBetAndSave(Long id, double cash);
	
	boolean validateBetInsideBasket(Long betId, Long userId);
	
	List<UserBet> showUserBets(boolean active, boolean finished);
	
	boolean deleteBetFromBasket(Long betId) throws Exception;
	
}
