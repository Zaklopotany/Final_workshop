package pl.coderslab.final_project.service;

import java.util.List;

import pl.coderslab.final_project.entity.bet.Bet;
import pl.coderslab.final_project.entity.bet.BetHistory;
import pl.coderslab.final_project.entity.bet.UserBet;
import pl.coderslab.final_project.entity.user.User;
import pl.coderslab.final_project.entity.user.Wallet;
import pl.coderslab.final_project.entity.user.WalletHistory;

public interface UserBetService {
	/**
	 * This method creates new inactive {@link UserBet} First, validate if the main
	 * {@link Bet} is active and create new UserBet object, gets newest
	 * {@link BetHistory}
	 * 
	 * and creates UserBet object throw and exception if bet is already in the
	 * "basket" otherwise save new UserBet and
	 * 
	 * @return UserBet
	 * 
	 */
	UserBet addBetToUserBasket(Long userId, Long betId) throws Exception;

	List<UserBet> getUserBasket(Long userId);

	/**
	 * This method active new bets by changing its status to active also change
	 * {@link Wallet} state and add new record in {@link WalletHistory}
	 * 
	 * if {@link User} does not have cash on his account @return false
	 * 
	 * if everything is correct
	 * 
	 * @return true
	 */
	boolean validateBetAndSave(Long id, double cash);

	boolean validateBetInsideBasket(Long betId, Long userId);

	List<UserBet> showUserBets(boolean active, boolean finished);

	/**
	 * Method delete bets from {@link User} "basket" - group inactive
	 * {@link UserBet}
	 * 
	 * checks whether it belongs to the user and deletes it if true
	 * 
	 * @return boolean true or throws exception
	 */

	boolean deleteBetFromBasket(Long betId) throws Exception;

}
