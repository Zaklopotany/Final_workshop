package pl.coderslab.final_project.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.final_project.entity.bet.Bet;
import pl.coderslab.final_project.entity.team.Match;
import pl.coderslab.final_project.repository.BetRepository;

@Controller
@RequestMapping("/bets")
public class BetController {
	
	@Autowired
	private BetRepository betRepository;

	@ModelAttribute("betsMatch")
	public Map<Match, List<Bet>> getBetsMatch(Long cat1, Long cat2) {
		Map<Match, List<Bet>> betsMap = new HashMap<>();
		//find matches where category is cat 1 and active
		List<Match> matches = new ArrayList<>();
		
		
		
		return betsMap;

	}

	@RequestMapping("/soccer/{category}/{subcategory}")
	public String loadSoccerBets(@PathVariable("category") String category,
			@PathVariable("subcategory") String subcategory) {
		// load all bets where sub and cat is like path var
		
		return "";
	}

}
