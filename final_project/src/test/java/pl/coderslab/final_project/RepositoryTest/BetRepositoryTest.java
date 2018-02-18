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

@DataJpaTest
@RunWith(SpringRunner.class)
public class BetRepositoryTest {
	@Autowired
	BetRepository betRepo;
	@Autowired
	TestEntityManager entityManager;
	
	
	Bet bet1 = new Bet();
	Bet bet2 = new Bet();
	Match match1 = new Match();
	Match match2 = new Match();
	LeagueCategory leaguCat = new LeagueCategory();
	NationCategory nationCat = new NationCategory();
	SportCategory sportCat = new SportCategory();
	BetCategory betCat1 = new BetCategory();
	BetCategory betCat2 = new BetCategory();
	BetSubCategory betSubCat1 = new BetSubCategory();
	BetSubCategory betSubCat2 = new BetSubCategory();
	
	@Before
	public void setUp() {
		leaguCat = entityManager.persistAndFlush(LeagueCategory.builder().leagueName("league").build());
		nationCat = entityManager.persistAndFlush(NationCategory.builder().name("nation").build());
		sportCat = entityManager.persistAndFlush(SportCategory.builder().name("sport").build());
		Team team1 = entityManager.persistAndFlush(Team.builder().leagueCategory(leaguCat).sportCategory(sportCat).nationCategory(nationCat).name("team1").build());
		Team team2 = entityManager.persistAndFlush(Team.builder().leagueCategory(leaguCat).sportCategory(sportCat).nationCategory(nationCat).name("team2").build());
		match1 = entityManager.persistAndFlush(Match.builder().teamHome(team1).teamAway(team2).active(true).build());
		match2 = entityManager.persistAndFlush(Match.builder().teamHome(team1).teamAway(team2).active(false).build());
		betCat1 = entityManager.persistAndFlush(BetCategory.builder().name("betCat1").build());
		betCat2 = entityManager.persistAndFlush(BetCategory.builder().name("betCat2").build());
		betSubCat1 = entityManager.persistAndFlush(BetSubCategory.builder().description("betSubCat1").value((byte) 1).build());
		betSubCat2 = entityManager.persistAndFlush(BetSubCategory.builder().description("betSubCat1").value((byte) 2).build());
		bet1 = entityManager.persistAndFlush(Bet.builder().match(match1).betCategory(betCat1).betSubCategory(betSubCat1).build());
		bet2 = entityManager.persistAndFlush(Bet.builder().match(match2).betCategory(betCat2).betSubCategory(betSubCat2).build());
	
	}
	
	@Test
	public void find_bets_by_matchCategory_and_matchSubCategory_and_active() {
		// given
		List<Bet> betList = new ArrayList<>();
		betList.add(bet1);
		//when
		entityManager.persist(Bet.builder().active(false).match(match1).betCategory(betCat1).betSubCategory(betSubCat1).build());
		entityManager.persist(Bet.builder().active(true).match(match1).betCategory(betCat1).betSubCategory(betSubCat2).build());
		List<Bet> betListActive = betRepo.getBetByMach(match1.getId(), true, betCat1.getId());
		List<Bet> betListInactive = betRepo.getBetByMach(match1.getId(), false, betCat1.getId());
		//then
		assertEquals(2, betListActive.size());
		assertEquals(1, betListInactive.size());
		assertEquals(1, betListActive.get(0).getBetSubCategory().getValue());
	}
	
	
	
	
}
