package pl.coderslab.final_project.web.rest;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.final_project.repository.MatchRepository;
import pl.coderslab.final_project.web.rest.dto.MatchDTO;

@RestController
@RequestMapping("/api/match")
public class MatchResource {
	private MatchRepository matchRepository;
	
	@Autowired
	MatchResource(MatchRepository matchRepository) {
		this.matchRepository = matchRepository; 
	}
	
	@RequestMapping(path="/active/{sportId}/{countryId}/{leagueId}", method=RequestMethod.GET)
	public List<MatchDTO> getMatchesYouCanBetOn(@PathVariable Long sportId, @PathVariable Long countryId, @PathVariable Long leagueId){
		return matchRepository.getMatchesBetAvailable(countryId, leagueId, sportId).stream()
				.map(match -> 
				new MatchDTO(match.getId(),
						match.getTeam_home().getName(),
						match.getTeam_away().getName(),
						match.getScore_home(),
						match.getScore_away()))
				.collect(Collectors.toList());
	}
	
}
