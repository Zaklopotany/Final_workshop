package pl.coderslab.final_project.web.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import pl.coderslab.final_project.entity.team.SportCategory;
import pl.coderslab.final_project.entity.team.Team;
import pl.coderslab.final_project.repository.TeamRepository;
import pl.coderslab.final_project.web.rest.dto.TeamDTO;

@RestController
@RequestMapping(value = "/api/team")
public class TeamResource {

	private TeamRepository teamRepository;
	

	TeamResource(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	@RequestMapping(path = "/{name}", method = RequestMethod.GET)
	public TeamDTO getTeamByName(@PathVariable("name") String name) {
		Team team = teamRepository.findOneByName(name);
		return TeamDTO.builder().league(team.getLeagueCategory().getLeagueName()).name(team.getName())
				.sport(team.getSportCategory().getName()).rating(team.getRating())
				.country(team.getNationCategory().getName()).build();
	}
	
//	@RequestMapping(path = "/raw/{name}", method = RequestMethod.GET)
//	public Team getTeamName(@PathVariable("name") String name) {
//		Team team = teamRepository.findOneByName(name);
//		return TeamDTO.builder().league(team.getLeagueCategory().getLeagueName()).name(team.getName())
//				.sport(team.getSportCategory().getName()).rating(team.getRating())
//				.country(team.getNationCategory().getName()).build();
//		return team;
//	}

	@RequestMapping(path = "/sport/{sportId}", method = RequestMethod.GET)
	public List<TeamDTO> teams(@PathVariable Long sportId) {
		SportCategory sportCategory = new SportCategory();
		sportCategory.setId(sportId);
		List<Team> teamList = teamRepository.findBySportCategory(sportCategory);
		return teamList.stream()
				.map(team -> new TeamDTO(team.getName(), team.getSportCategory().getName(),
						team.getNationCategory().getName(), team.getLeagueCategory().getLeagueName(), team.getRating()))
				.collect(Collectors.toList());
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Team createTeam(@RequestBody Team team) {
		return teamRepository.save(team);
	}
	
	

}
