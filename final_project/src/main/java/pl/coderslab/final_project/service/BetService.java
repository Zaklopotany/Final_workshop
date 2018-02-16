package pl.coderslab.final_project.service;

import java.util.Collection;

import pl.coderslab.final_project.entity.bet.Bet;
import pl.coderslab.final_project.entity.bet.BetCategory;
import pl.coderslab.final_project.entity.bet.BetSubCategory;
import pl.coderslab.final_project.entity.team.Match;

public interface BetService {
	Bet addNewBet(Match match, BetCategory betCategory, BetSubCategory betSubCategory);
	void updateAllCurrentBets();
	void updateBet(Bet bet);
	void closeBets(Collection<Bet> bets);
	
}
