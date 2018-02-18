package pl.coderslab.final_project.service;

import pl.coderslab.final_project.entity.bet.UserBet;

public interface WalletService {

	public void rechargeWallet(double cash) throws Exception;
	public void payThePrize(UserBet userBet) throws Exception;
	
}
