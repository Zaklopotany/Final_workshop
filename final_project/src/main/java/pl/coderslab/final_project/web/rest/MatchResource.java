package pl.coderslab.final_project.web.rest;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.final_project.repository.MatchRepository;
import pl.coderslab.final_project.service.MatchService;
import pl.coderslab.final_project.web.rest.dto.MatchDTO;

@RestController
@RequestMapping("/api/match")
public class MatchResource {
	private MatchRepository matchRepository;
	private MatchService matchService;
	
	@Autowired
	MatchResource(MatchRepository matchRepository, MatchService matchService) {
		this.matchRepository = matchRepository; 
		this.matchService = matchService;
	}
	
	@RequestMapping(path="/active/{sportId}/{countryId}/{leagueId}", method=RequestMethod.GET)
	public List<MatchDTO> getMatchesYouCanBetOn(@PathVariable Long sportId, @PathVariable Long countryId, @PathVariable Long leagueId){
		return matchRepository.getMatchesBetAvailable(countryId, leagueId, sportId).stream()
				.map(match -> 
				new MatchDTO(match.getId(),
						match.getTeamHome().getName(),
						match.getTeamAway().getName(),
						match.getScoreHome(),
						match.getScoreAway()))
				.collect(Collectors.toList());
	}
	
	@GetMapping(path="/endMatch/{matchId}")
	public void endMatch (@PathVariable Long matchId) {
		matchService.endMatch(matchId);
	}
	
}
