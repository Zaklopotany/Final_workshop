package pl.coderslab.final_project.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.coderslab.final_project.entity.bet.Bet;
import pl.coderslab.final_project.entity.bet.BetHistory;
import pl.coderslab.final_project.entity.bet.UserBet;
import pl.coderslab.final_project.entity.user.User;
import pl.coderslab.final_project.entity.user.Wallet;
import pl.coderslab.final_project.entity.user.WalletHistory;
import pl.coderslab.final_project.repository.BetHistoryRepository;
import pl.coderslab.final_project.repository.BetRepository;
import pl.coderslab.final_project.repository.UserBetRepository;
import pl.coderslab.final_project.repository.UserRepository;
import pl.coderslab.final_project.repository.WalletRepository;
import pl.coderslab.final_project.repository.WallethistoryRepository;

@Service
@AllArgsConstructor
public class UserBetServiceImpl implements UserBetService {
		@Autowired
		private UserRepository userRepo;
		@Autowired
		private BetHistoryRepository betHistoryRepo;
		@Autowired
		private UserBetRepository userBetRepo;
		@Autowired
		private BetRepository betRepo;
		@Autowired
		private WalletRepository walletRepo;
		@Autowired
		private WallethistoryRepository walletHistoryRepo;
		
		private static final double MIN_BET_VALUE= 3.0;
		
	//TODO make custom Exceptions
		
		
	@Override
	public UserBet addBetToUserBasket(Long userId, Long betId) throws Exception {
		Bet bet = betRepo.findOne(betId);
		User user = userRepo.findOne(userId);
		if(bet == null || bet.isActive()==false) {
			throw new Exception("No such bet or not active");
		}
		UserBet userBet = new UserBet();
		BetHistory betHistory = betHistoryRepo.findFirstByBetOrderByCreatedDesc(bet);
		if (!validateBetInsideBasket(betId, userId)) {
			throw new Exception("Bet already in basket");
		}
		
		userBet.setBet(bet);
		userBet.setUser(user);
		userBet.setBetHistory(betHistory);
		userBet.setCreated(new Timestamp(System.currentTimeMillis()));
		userBetRepo.save(userBet);
		
		return userBet;
	}



	@Override
	public List<UserBet> getUserBasket(Long userId) {
		return userBetRepo.getUserBasket(userId);
	}


	
	@Override
	public boolean validateBetAndSave(Long id, double cash) {
		CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserBet userBet = userBetRepo.getOne(id);
		if (userBet.getUser().getId() != user.getUserId() || userBet.getBet().isActive() == false) {
			return false;
		}
		//check wallet
		Wallet userWallet = walletRepo.findOneByOwnerId(user.getUserId());		
		if (userWallet.getAccState() < cash || cash < MIN_BET_VALUE) {
			return false;
		} else {
			WalletHistory walletHistory = new WalletHistory();
			walletHistory.setWallet(userWallet);
			walletHistory.setOperationName(userBet.getBet().toString());
			walletHistory.setOperationValue(cash);
			
			userWallet.setAccState(userWallet.getAccState()-cash);
			walletRepo.save(userWallet);
			walletHistoryRepo.save(walletHistory);
		}
		
		userBet.setMoney(cash);
		userBet.setActive(true);
		userBetRepo.save(userBet);
			
		return true;
	}
	

	@Override
	public boolean validateBetInsideBasket(Long betId, Long userId) {
		List<UserBet> basket = getUserBasket(userId);
		Bet bet = betRepo.findOne(betId);
		for(UserBet userBet: basket) {
			if (userBet.getBet().getId() == bet.getId()) {
				if(userBet.getBetHistory().getId() == betHistoryRepo.findFirstByBetOrderByCreatedDesc(bet).getId()) {
					return false;
				}
			}
		}
		return true;
	}



	@Override
	public List<UserBet> showUserBets(boolean active, boolean finished) {
		Long userId = ((CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
		return userBetRepo.getUserBets(userId, active, finished);
	}


	
	
	
	//TODO make custom Exceptions
	@Override
	public boolean deleteBetFromBasket(Long betId) throws Exception {
		Long userId = ((CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
		UserBet userBet = userBetRepo.getOne(betId);
		if (userBet.getUser().getId() != userId || userBet.isActive() == true) {
			throw new Exception("Cannot Delete");
		} else {
			userBetRepo.delete(userBet);
		}
		return true;
	}
	





	
	
}
