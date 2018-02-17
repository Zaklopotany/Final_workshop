package pl.coderslab.final_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import pl.coderslab.final_project.entity.bet.UserBet;
import pl.coderslab.final_project.entity.user.Wallet;
import pl.coderslab.final_project.entity.user.WalletHistory;
import pl.coderslab.final_project.repository.WalletRepository;
import pl.coderslab.final_project.repository.WallethistoryRepository;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepository walletRepo;
	@Autowired
	private WallethistoryRepository walletHistoryRepo;

	@Override
	public void rechargeWallet(double cash) {
		CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Wallet wallet = walletRepo.findOneByOwnerId(user.getUserId());
		WalletHistory walletHistory = new WalletHistory();
		walletHistory.setOperationName("fill");
		if (cash >= 0.0) {
			wallet.setAccState(wallet.getAccState() + cash);
			walletRepo.save(wallet);
			walletHistory.setOperationValue(cash);
			walletHistory.setWallet(wallet);
			walletHistoryRepo.save(walletHistory);
		} else {
			// throws new Exception("amount cannot be ")
		}
	}

	@Override
	public void payThePrize(UserBet userBet) {
		WalletHistory walletHistory = new WalletHistory();
		Wallet wallet = walletRepo.findOneByOwnerId(userBet.getUser().getId());
		walletHistory.setOperationName("Bet won! : " + userBet.getBet().getBetCategory().getName());
		double prize = userBet.getMoney() * userBet.getBetHistory().getRating();
		if (prize >= 0.0) {
			wallet.setAccState(wallet.getAccState() + prize);
			walletRepo.save(wallet);
			walletHistory.setOperationValue(prize);
			walletHistory.setWallet(wallet);
			walletHistoryRepo.save(walletHistory);
		} else {
			// throws new Exception("amount cannot be ")
		}
	}

}
