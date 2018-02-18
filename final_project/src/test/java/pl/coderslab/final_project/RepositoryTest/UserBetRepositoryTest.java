package pl.coderslab.final_project.RepositoryTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
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
import pl.coderslab.final_project.entity.bet.UserBet;
import pl.coderslab.final_project.entity.team.LeagueCategory;
import pl.coderslab.final_project.entity.team.Match;
import pl.coderslab.final_project.entity.team.NationCategory;
import pl.coderslab.final_project.entity.team.SportCategory;
import pl.coderslab.final_project.entity.team.Team;
import pl.coderslab.final_project.entity.user.User;
import pl.coderslab.final_project.repository.BetRepository;
import pl.coderslab.final_project.repository.UserBetRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class UserBetRepositoryTest {
	
	@Autowired
	BetRepository betRepo;
	@Autowired
	TestEntityManager entityManager;
	@Autowired
	UserBetRepository userBetRepo;
	
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
	User user = new User();
	User user1 = new User();
	
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
		user = entityManager.persistAndFlush(User.builder().email("mim@wp.pl").login("login").build());
		user1 = entityManager.persistAndFlush(User.builder().email("mim1@wp.pl").login("login1").build());
		
	}
	
	//user basket -  inactive bets
		@Test
		public void find_user_inactive_bets() {
			//given
			UserBet userBet1 = new UserBet();
			UserBet userBet2 = new UserBet();
			UserBet userBet3 = new UserBet();
			userBet1.setBet(bet1);
			userBet2.setBet(bet1);
			userBet3.setBet(bet1);
			userBet1.setUser(user);
			userBet2.setUser(user1);
			userBet3.setUser(user);
			userBet3.setActive(true);
			//when
			userBet1 = entityManager.persistAndFlush(userBet1);
			userBet2 = entityManager.persistAndFlush(userBet2);
			userBet3 = entityManager.persistAndFlush(userBet3);
			List<UserBet> userBetList = Arrays.asList(userBet1);
			List<UserBet> userBetInactive = Arrays.asList(userBet3);
			//then
			assertEquals(userBetList, userBetRepo.getUserBasket(user.getId()));
			assertEquals(userBetInactive,userBetRepo.getUserBets(user.getId(), true, false));
			
		}
}
