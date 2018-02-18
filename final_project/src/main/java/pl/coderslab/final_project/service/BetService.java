package pl.coderslab.final_project.service;

import java.util.Collection;

import pl.coderslab.final_project.entity.bet.Bet;
import pl.coderslab.final_project.entity.bet.BetCategory;
import pl.coderslab.final_project.entity.bet.BetSubCategory;
import pl.coderslab.final_project.entity.bet.UserBet;
import pl.coderslab.final_project.entity.team.Match;

public interface BetService {
	Bet addNewBet(Match match, BetCategory betCategory, BetSubCategory betSubCategory);

	void updateAllCurrentBets();

	void updateBet(Bet bet);

	/**
	 * this method set {@link Bet} into inactive state Users cannot bid, it also
	 * deletes all inactive
	 * 
	 * {@link UserBet} from the basket
	 */
	void closeBets(Collection<Bet> bets);

}
