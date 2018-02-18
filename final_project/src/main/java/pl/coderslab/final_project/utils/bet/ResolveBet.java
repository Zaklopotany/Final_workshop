package pl.coderslab.final_project.utils.bet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.coderslab.final_project.entity.bet.Bet;
import pl.coderslab.final_project.entity.bet.UserBet;
import pl.coderslab.final_project.repository.BetRepository;
import pl.coderslab.final_project.repository.UserBetRepository;
import pl.coderslab.final_project.service.WalletService;

/**
 * this class contains methods that provides resolving normal bet calculating
 * win etc.
 * 
 * @author zaklopotany
 *
 */
@Component
public class ResolveBet {

	@Autowired
	BetRepository betRepo;
	@Autowired
	UserBetRepository userBetRepo;
	@Autowired
	WalletService walletService;

	@Scheduled(fixedRate = 600000)
	public void resolveNormalBets() throws Exception {
		List<Bet> betList = betRepo.findAllByFinishedAndResolved(true, false);

		for (Bet bet : betList) {
			List<UserBet> userBetList = userBetRepo.findAllByBetAndActive(bet, true);
			for (UserBet userBet : userBetList) {
				sortToBetCategories(userBet);
			}
			bet.setResolved(true);
			betRepo.save(bet);
		}


	}

	// TODO refactor
	public void sortToBetCategories(UserBet userBet) throws Exception {
		switch (userBet.getBet().getBetCategory().getName()) {
		case "match":
			checkMatchCondition(userBet);
			break;
		default:
			break;
		}
	}

	public void checkMatchCondition(UserBet userBet) throws Exception {
		int scoreHome = userBet.getBet().getMatch().getScoreHome();
		int scoreAway = userBet.getBet().getMatch().getScoreAway();

		switch (userBet.getBet().getBetSubCategory().getDescription()) {
		case "Home win":
			if (scoreHome > scoreAway) {
				walletService.payThePrize(userBet);
				userBet.setWon(true);
			}
			break;
		case "Tie":
			if (scoreHome == scoreAway) {
				walletService.payThePrize(userBet);
				userBet.setWon(true);
			}
			break;
		case "Away win":
			if (scoreHome < scoreAway) {
				walletService.payThePrize(userBet);
				userBet.setWon(true);
			}
			break;
		default:
			break;
		}
		userBet.setFinished(true);
		userBetRepo.save(userBet);
	}

	

}
