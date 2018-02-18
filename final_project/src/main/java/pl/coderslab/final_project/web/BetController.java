package pl.coderslab.final_project.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.final_project.entity.bet.Bet;
import pl.coderslab.final_project.entity.bet.UserBet;
import pl.coderslab.final_project.entity.team.Match;
import pl.coderslab.final_project.entity.user.User;
import pl.coderslab.final_project.repository.BetRepository;
import pl.coderslab.final_project.repository.MatchRepository;
import pl.coderslab.final_project.repository.UserBetRepository;
import pl.coderslab.final_project.repository.UserRepository;
import pl.coderslab.final_project.service.CurrentUser;
import pl.coderslab.final_project.service.UserBetService;

/**
 * BetController contains actions connected with betting and user participation
 * 
 * @author zaklopotany
 *
 */

@Controller
@RequestMapping("/bets")
public class BetController {
	// 1L is soccer id in DB
	private final Long SOCCER_ID = 1L;

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BetRepository betRepo;
	@Autowired
	private MatchRepository matchRepo;
	@Autowired
	private UserBetRepository userBetRepo;
	@Autowired
	private UserBetService userBetService;

	@ModelAttribute("betsCat")
	public Map<Match, List<Bet>> getBetsMatch(Long sport, Long nation, Long league, Long betCat) {
		Map<Match, List<Bet>> betsMap = new HashMap<>();
		List<Match> matches = new ArrayList<>();
		// find matches where category is cat 1 and active
		matches = matchRepo.getMatchByBetCategory(sport, league, nation, betCat);
		// loop that will load subCategory bets (view purpose)
		for (Match match : matches) {
			betsMap.put(match, betRepo.getBetByMach(match.getId(),true, 1L));
		}
		return betsMap;
	}
	
	public String generateBackURL(Long betId) {
		Bet tempBet = betRepo.findOne(betId);
		StringBuffer buf = new StringBuffer();
		buf.append("/").append(tempBet.getMatch().getTeamHome().getSportCategory().getName()).append("/")
				.append(tempBet.getMatch().getTeamHome().getNationCategory().getId()).append("/")
				.append(tempBet.getMatch().getTeamHome().getLeagueCategory().getId()).append("/")
				.append(tempBet.getBetCategory().getId());
		return buf.toString();
	}

	/**
	 * Loads soccer bets according to 3 params: team: nation and
	 * league
	 * 
	 * @param nation
	 * @param league
	 * @param betCat
	 * @param model
	 * @return
	 */

	@GetMapping("/soccer/{nation}/{league}/{betCat}")
	public String loadSoccerBets(@PathVariable("nation") Long nation, @PathVariable("league") Long league,
			@PathVariable(value = "betCat") Long betCat, Model model) {
		CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("betsCat", getBetsMatch(SOCCER_ID, nation, league, betCat));
		model.addAttribute("basket", userBetService.getUserBasket(user.getUserId()));

		return "bets/bets";
	}
	
	

	// add new bet to userbet basket
	@GetMapping("/basket/{betId}")
	public String putIntoBasket(@PathVariable("betId") Long betId, Model model) throws Exception {
		CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// add bet to basket
		try {
			userBetService.addBetToUserBasket(user.getUserId(), betId);
		} catch (Exception e) {}
		
		return "redirect:/bets" + generateBackURL(betId);
	}

	/**
	 * Return the view with user basket - bets awaiting acceptance
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/basket")
	public String loadBasket(Model model) {
		CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// update user basket
		model.addAttribute("basket", userBetService.getUserBasket(user.getUserId()));
		return "user/basket";
	}

	@PostMapping("/bet")
	public String saveBet(@RequestParam("cash") double cash, @RequestParam("betId") Long betId, Model model) {
		CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userBetService.validateBetAndSave(betId, cash)) {
			model.addAttribute("message", "Operaion succed, thank you");
		} else {
			model.addAttribute("message", "Operaion failed, please fill you wallet ");
		}
		
		model.addAttribute("basket", userBetService.getUserBasket(user.getUserId()));
		return "user/basket";
	}

	/*
	  @GetMapping("/soccer")
	  
	  @ResponseBody public Map<Match, List<Bet>> loadSoccerBets1() { // load all
	  bets where sub and cat is like path var Map<Match, List<Bet>> betsy =
	  getBetsMatch(1L, 1L, 1L, 1L); Set<Match> sety = betsy.keySet(); //
	  betsy.get(sety) return getBetsMatch(1L, 1L, 1L, 1L); // return "login/login";
	  }
	 */
}
