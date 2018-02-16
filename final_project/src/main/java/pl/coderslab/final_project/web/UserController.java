package pl.coderslab.final_project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.final_project.entity.user.Wallet;
import pl.coderslab.final_project.repository.WalletRepository;
import pl.coderslab.final_project.repository.WallethistoryRepository;
import pl.coderslab.final_project.service.CurrentUser;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private WalletRepository walletRepo;
	@Autowired
	private WallethistoryRepository walletHistoryRepo;
	
	@RequestMapping("/home")
	public String getUserHome() {		
		return "user/text";
	}
	
	@GetMapping("/wallet")
	public String showWalletDetails(Model model) {
		CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Wallet wallet = walletRepo.findOneByOwnerId(user.getUserId());
		model.addAttribute("wallet",wallet);
		model.addAttribute("walletHistory",walletHistoryRepo.findByWalletId(wallet.getId()));
		return "user/wallet";
	}
	
	
}
