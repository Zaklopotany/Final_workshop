package pl.coderslab.final_project.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import pl.coderslab.final_project.entity.bet.Bet;
import pl.coderslab.final_project.entity.bet.BetCategory;
import pl.coderslab.final_project.entity.bet.BetSubCategory;
import pl.coderslab.final_project.entity.team.Match;
import pl.coderslab.final_project.repository.BetRepository;
import pl.coderslab.final_project.repository.UserBetRepository;

public class BetServiceImpl implements BetService {

	private BetRepository betRepo;
	private UserBetRepository userBetRepo;

	@Autowired
	public BetServiceImpl(BetRepository betRepo, UserBetRepository userBetRepo) {
		this.betRepo = betRepo;
		this.userBetRepo = userBetRepo;
	}
	
	@Override
	public Bet addNewBet(Match match, BetCategory betCategory, BetSubCategory betSubCategory) {
		//TODO implement
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

	
	@Override
	public void closeBets(Collection<Bet> bets) {
		bets.stream().map(bet -> bet.setActive(false)).forEach(bet -> betRepo.save(bet));
		//find user inactive bets and delete
		for (Bet bet : bets) {
			userBetRepo.findAllByBetAndActive(bet, false).stream().forEach(userBet -> userBetRepo.delete(userBet));
		}

	}

}
