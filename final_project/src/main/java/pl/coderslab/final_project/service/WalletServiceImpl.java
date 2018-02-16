package pl.coderslab.final_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import pl.coderslab.final_project.entity.user.Wallet;
import pl.coderslab.final_project.repository.WalletRepository;

public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepository walletRepo;
	
	@Override
	public void rechargeWallet(double cash) {
		CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Wallet wallet = walletRepo.findOneByOwnerId(user.getUserId());	
		if (cash >= 0.0) {
			wallet.setAccState(wallet.getAccState() + cash);			
		} else {
			//throws new Exception("amount cannot be ")
		}
	}

}
