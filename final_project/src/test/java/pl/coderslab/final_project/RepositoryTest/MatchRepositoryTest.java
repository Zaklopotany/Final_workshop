package pl.coderslab.final_project.RepositoryTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import pl.coderslab.final_project.entity.bet.Bet;
import pl.coderslab.final_project.entity.bet.BetCategory;
import pl.coderslab.final_project.entity.bet.BetSubCategory;
import pl.coderslab.final_project.entity.team.LeagueCategory;
import pl.coderslab.final_project.entity.team.Match;
import pl.coderslab.final_project.entity.team.NationCategory;
import pl.coderslab.final_project.entity.team.SportCategory;
import pl.coderslab.final_project.entity.team.Team;
import pl.coderslab.final_project.repository.BetRepository;
import pl.coderslab.final_project.repository.MatchRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class MatchRepositoryTest {
	@Autowired
	BetRepository betRepo;
	@Autowired
	MatchRepository matchRepo;
	@Autowired
	private TestEntityManager entityManager;
	
	Match match1 = new Match();
	Match match2 = new Match();
	LeagueCategory leaguCat = new LeagueCategory();
	NationCategory nationCat = new NationCategory();
	SportCategory sportCat = new SportCategory();
	
	
	@Before
	public void setup() {
		leaguCat = entityManager.persistAndFlush(LeagueCategory.builder().leagueName("league").build());
		nationCat = entityManager.persistAndFlush(NationCategory.builder().name("nation").build());
		sportCat = entityManager.persistAndFlush(SportCategory.builder().name("sport").build());
		Team team1 = entityManager.persistAndFlush(Team.builder().leagueCategory(leaguCat).sportCategory(sportCat).nationCategory(nationCat).name("team1").build());
		Team team2 = entityManager.persistAndFlush(Team.builder().leagueCategory(leaguCat).sportCategory(sportCat).nationCategory(nationCat).name("team2").build());
		match1 = entityManager.persistAndFlush(Match.builder().teamHome(team1).teamAway(team2).active(true).build());
		match2 = entityManager.persistAndFlush(Match.builder().teamHome(team1).teamAway(team2).active(false).build());
	}
	
	//Matches that system can create bets on
	@Test
	public void find_active_matches_by_leaguCat_sportCat_nationCat() {
		//given
		List<Match> matchActiveList = new ArrayList<>();
		matchActiveList.add(match1);
		//when
		List<Match> matchList = matchRepo.findAll();
		//then
		assertEquals(2, matchList.size());
		assertEquals(matchActiveList, matchRepo.getMatchesBetAvailable(sportCat, leaguCat, nationCat));
		
	}
	
	
	//matches that user can bid on
	@Test
	public void find_active_matches_by_betCategory_league_nation_sport() {
		//given
		BetCategory betCat = entityManager.persistAndFlush(BetCategory.builder().name("betCat1").build());
		BetSubCategory betSubCat = entityManager.persistAndFlush(BetSubCategory.builder().description("betSubCat1").build());
		Bet bet = entityManager.persistAndFlush(Bet.builder().betCategory(betCat).betSubCategory(betSubCat).active(true).match(match1).build());
		Bet bet2 = entityManager.persistAndFlush(Bet.builder().betCategory(betCat).betSubCategory(betSubCat).active(true).match(match2).build());
		List<Match> matchActiveList = new ArrayList<>();
		matchActiveList.add(match1);
		matchActiveList.add(match2);

		//when
		List<Match> matchList = matchRepo.getMatchByBetCategory(sportCat, leaguCat, nationCat, betCat);
		//then
		assertEquals(2, matchList.size());
		assertEquals(matchActiveList, matchList);
	}
	

	
	
	
	
}
