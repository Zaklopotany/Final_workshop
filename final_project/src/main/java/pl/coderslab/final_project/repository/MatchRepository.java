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
	@Query("Select m from Bet b Join b.match m Join m.teamHome t where t.sportCategory.id =:sportCat "
			+ "and t.leagueCategory.id =:leagueCat " + "and t.nationCategory.id =:nationCat " + "and b.betCategory.id =:betCat " + "and b.active = true") 
	List<Match> getMatchByBetCategory(@Param("sportCat") Long sportCat,
			@Param("leagueCat") Long leaguCat, @Param("nationCat") Long nationCat, @Param("betCat") Long betCat);

	@Query("Select m from Match m Join m.teamHome t " + "where t.sportCategory.id =:sportCat "
			+ "and t.leagueCategory.id =:leagueCat " + "and t.nationCategory.id =:nationCat " + "and m.active = true")
	List<Match> getMatchesBetAvailable(@Param("sportCat") Long sportCat,
			@Param("leagueCat") Long leaguCat, @Param("nationCat") Long nationCat);
}
