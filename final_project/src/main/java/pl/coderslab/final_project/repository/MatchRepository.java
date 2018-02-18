package pl.coderslab.final_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.coderslab.final_project.entity.bet.BetCategory;
import pl.coderslab.final_project.entity.team.LeagueCategory;
import pl.coderslab.final_project.entity.team.Match;
import pl.coderslab.final_project.entity.team.NationCategory;
import pl.coderslab.final_project.entity.team.SportCategory;

public interface MatchRepository extends JpaRepository<Match, Long> {
	/**
	 * This is super mega method to connect Bets with matches, purpose of this method
	 * is to provide view data
	 * 
	 * @param category
	 * @param sport
	 * @param league
	 * @param batCat
	 * @return list of Matches that match above parameters
	 */
	@Query("Select m from Bet b Join b.match m Join m.teamHome t where t.sportCategory =:sportCat "
			+ "and t.leagueCategory =:leagueCat " + "and t.nationCategory =:nationCat " + "and b.betCategory =:betCat " + "and b.active = true") 
	List<Match> getMatchByBetCategory(@Param("sportCat") SportCategory sportCat,
			@Param("leagueCat") LeagueCategory leaguCat, @Param("nationCat") NationCategory nationCat, @Param("betCat") BetCategory betCat);

	@Query("Select m from Match m Join m.teamHome t " + "where t.sportCategory =:sportCat "
			+ "and t.leagueCategory =:leagueCat " + "and t.nationCategory =:nationCat " + "and m.active = true")
	List<Match> getMatchesBetAvailable(@Param("sportCat") SportCategory sportCat,
			@Param("leagueCat") LeagueCategory leaguCat, @Param("nationCat") NationCategory nationCat);
}
