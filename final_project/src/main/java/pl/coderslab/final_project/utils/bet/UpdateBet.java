package pl.coderslab.final_project.utils.bet;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import pl.coderslab.final_project.entity.bet.Bet;
import pl.coderslab.final_project.entity.team.Match;
import pl.coderslab.final_project.repository.BetRepository;
import pl.coderslab.final_project.repository.MatchRepository;

/**
 * This class contains method responsible for updating bet rating
 * 
 * @author zaklopotany
 *
 */
@Component
public class UpdateBet {


	@Autowired
	private BetRepository betRepo;

	/**
	 * Method takes {@link Match} as a parameter and then
	 * search for methods assigned to sport category to 
	 * calculate new bet rate
	 * 
	 * @param match
	 * @return 
	 */
	
	//TODO refactor thread safe?
	@Async
	public Future<Long> updateHomeScore(Match match) {
		long start = System.currentTimeMillis();
		switch (match.getTeamHome().getSportCategory().getName()) {
		case "football":
			updateFootballBetRating(match);
			break;
		}
		
		return new AsyncResult<>(System.currentTimeMillis() - start);
	}
	
	
	
	/**
	 * This method calculates new rate
	 * @param match
	 */
	public void updateFootballBetRating(Match match) {
		List<Bet> betList = betRepo.findAllByMatchAndActive(match, true);
		for (Bet bet : betList) {
			switch (bet.getBetCategory().getName()) {
			case "match":
				switch (bet.getBetSubCategory().getDescription()) {
				case "Home win":
					bet.setRate((bet.getRate() + 0.2) / 1.2);
					break;
				case "Tie":
					// no idea what should i do here ; ( let's say tie is equally probable
					// in every scenario, p.s. i know it isn't, i have to do it quick and easy
					break;
				case "Away win":
					bet.setRate((bet.getRate() / 2) + (bet.getRate() * 3) / 4);
					break;
				}
			}
			betRepo.save(bet);
		}

	}

}
