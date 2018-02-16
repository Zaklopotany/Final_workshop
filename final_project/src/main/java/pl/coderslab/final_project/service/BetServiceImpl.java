package pl.coderslab.final_project.service;

import java.util.Collection;

import pl.coderslab.final_project.entity.bet.Bet;
import pl.coderslab.final_project.entity.bet.BetCategory;
import pl.coderslab.final_project.entity.bet.BetSubCategory;
import pl.coderslab.final_project.entity.team.Match;

public class BetServiceImpl implements BetService{
	
	@Override
	public Bet addNewBet(Match match, BetCategory betCategory, BetSubCategory betSubCategory) {
		return null;
	}

	@Override
	public void updateAllCurrentBets() {
		// TODO implement
	}

	@Override
	public void updateBet(Bet bet) {
		// TODO implement
	}

	
	/**
	 * this method set {@link Bet} into inactive state
	 * Users cannot bet, it also deletes all inactive
	 * {@link UserBet} from the basket
	 */
	@Override
	public void closeBets(Collection<Bet> bets) {
		
		// TODO Auto-generated method stub
		
	}

}
